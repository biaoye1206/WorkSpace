package com.tianj.autowash.service.order;
import com.tianj.autowash.constant.ConstWeChat;
import com.tianj.autowash.constant.OrderStatus;
import com.tianj.autowash.constant.PayType;
import com.tianj.autowash.dao.coupon.UserCouponDao;
import com.tianj.autowash.dao.device.DeviceDao;
import com.tianj.autowash.dao.order.OrderDao;
import com.tianj.autowash.dao.server.ServerDao;
import com.tianj.autowash.entity.coupon.UserCoupon;
import com.tianj.autowash.entity.device.Device;
import com.tianj.autowash.entity.order.OrderDetail;
import com.tianj.autowash.entity.order.SysOrder;
import com.tianj.autowash.entity.server.Server;
import com.tianj.autowash.entity.user.User;
import com.tianj.autowash.basic.BaseSupport;
import com.tianj.autowash.utils.DataTools;
import com.tianj.autowash.utils.HttpTools;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Zhangxq
 * @version v1.0
 * @update 2019-01-19 14:59
 */
@Service
public class OrderServiceImpl extends BaseSupport implements OrderService {
    private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private DeviceDao deviceDao;
    @Autowired
    private ServerDao serverDao;
    @Autowired
    private UserCouponDao userCouponDao;
    @Autowired
    private OrderDao orderDao;

    /**
     * 生成订单详情
     *
     * @param qrCode 二维码信息
     * @return 详情
     */
    @Override
    public OrderDetail orderDetail(String qrCode, User user) {

        // 查询硬件信息
        Device device = deviceDao.findByDevId(qrCode);
        // 服务站信息
        Server server = serverDao.findById(device.getServiceStationId());
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setFacilityId(device.getFacilityId());
        orderDetail.setServiceStationId(server.getId());
        orderDetail.setServiceStationName(server.getName());
        orderDetail.setServiceStationAddr(server.getAddress());
        orderDetail.setAmount(BigDecimal.valueOf(9.9));
        orderDetail.setPayTypes(PayType.getPayTypes());
        // 获取用户优惠券信息
        List<UserCoupon> userCoupons = userCouponDao.findValidCouponsByUserId(user.getId());
        orderDetail.setCoupon(userCoupons);
        return orderDetail;
    }

    /**
     * 更新订单
     *
     * @param order   订单
     * @param order 订单对象
     */
    @Override
    public void update(SysOrder order) {
        dataProcess(order);
        orderDao.update(order);
    }

    /**
     * 根据Id查询订单信息
     *
     * @param id Id
     * @return 订单信息
     */
    @Override
    public SysOrder findById(String id) {
        return orderDao.findById(id);
    }

    /**
     * 根据用户ID查询订单列表
     *
     * @param userId 用户ID
     * @return 订单列表
     */
    @Override
    public List<SysOrder> getOrderListByUserId(String userId) {
        return orderDao.getOrderListByUserId(userId);
    }

    /**
     * 更新订单
     *
     * @param map 微信回调结果
     */
    @Override
    public void updateOrderInfo(Map<String, String> map, SysOrder order) {
        order.setTransactionId(map.get("transaction_id"));
        order.setPayAmout(BigDecimal.valueOf(Double.valueOf(map.get("cash_fee")) / 100));
        order.setOrderResult(OrderStatus.ORDER_STATUS_COMPLETE);
        dataProcess(order);
        orderDao.update(order);
    }

    /**
     * 创建定单
     *
     * @param order 订单信息
     * @return 支付时使用信息
     */
    @Override
    public Map<String, String> createOrder(SysOrder order, User user) {
        final String wash = "0";
        Map<String, String> result = null;
        String resultCode = "SUCCESS";
        try {
            // 设置订单用户信息
            order.setUsrUserId(user.getId());
            // 持久化订单
            dataProcess(order);
            if (wash.equals(order.getTradeType())){
                orderDao.weChatInsert(order);
            }else {
                orderDao.recharge(order);
            }
            Map<String, String> res = unifiedOrder(order.getFacilityId(), user.getId(), order.getId(), user.getOpenid());
            // 统一下单返回数据签名
            String orderId = order.getId();
            String uniSign = res.remove("sign");
            String calUniSign = DataTools.dataSign(res);
            String code = res.get("result_code");
            if (resultCode.equals(code) && uniSign.equals(calUniSign)) {
                String prepayId = res.get("prepay_id");
                result = getPayInfo(prepayId);
                result.put("orderId", orderId);
            } else {
                result = new HashMap<>(1);
                result.put("msg", "系统错误");
            }
        } catch (IOException e) {
            log.error("统一下单IO错误", e.toString());
        }
        return result;

    }

    /**
     * 微信统一下单
     *
     * @param facilityId 硬件ID
     * @param attach     附加数据
     * @param outTradeNo 商户订单号
     * @param openid     用户标识
     * @return
     * @throws IOException
     */
    private Map<String, String> unifiedOrder(String facilityId, String attach, String outTradeNo, String openid) throws IOException {
        HttpTools httpTools = new HttpTools();
        Map<String, String> body = new TreeMap<>(String::compareTo);
        // 小程序ID
        body.put("appid", ConstWeChat.APP_ID);
        // 商户号
        body.put("mch_id", ConstWeChat.MCH_ID);
        // 设备号
        if (facilityId != null) {
            body.put("device_info", facilityId);
        }
        // 随机字符串
        body.put("nonce_str", DataTools.nonceStr());
        // 签名类型
        body.put("sign_type", "MD5");
        // 商品描述
        body.put("body", "骄子智能洗车服务");
        // 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
        body.put("attach", attach);
        // 商户订单号
        body.put("out_trade_no", outTradeNo);
        // 标价金额 (分)
        body.put("total_fee", String.valueOf((int) (0.01 * 100)));
        // 终端IP
        body.put("spbill_create_ip", DataTools.getLocalAddress());
        // 通知地址
        body.put("notify_url", "https://www.cntjzz.com:8008/autowash/notify/weChat/" + outTradeNo);
        // 交易类型  （小程序 JSAPI）
        body.put("trade_type", "JSAPI");
        // 指定支付方式(不使用信用卡 no_credit)
        body.put("limit_pay", "no_credit");
        // 用户标识
        body.put("openid", openid);
        // 签名
        body.put("sign", DataTools.dataSign(body));
        String result = httpTools.http("https://api.mch.weixin.qq.com/pay/unifiedorder", HttpMethod.POST, body, null);
        return xmlStr2Map(result);
    }

    /**
     * 获取微信请求支付时使用信息
     *
     * @param prepayId 统一下单ID
     * @return 支付时使用信息
     */
    private Map<String, String> getPayInfo(String prepayId) {
        Map<String, String> payMent = new TreeMap<>(String::compareTo);
        // 小程序appid 只是用来签名
        payMent.put("appId", ConstWeChat.APP_ID);
        // 时间戳
        payMent.put("timeStamp", String.valueOf(System.currentTimeMillis()));
        // 随机字符串
        payMent.put("nonceStr", DataTools.nonceStr());
        // 统一下单接口返回的 prepay_id 参数
        payMent.put("package", "prepay_id=" + prepayId);
        // 签名算法
        payMent.put("signType", "MD5");
        // 对数据签名
        String sign = DataTools.dataSign(payMent);
        // 签名
        payMent.put("paySign", sign);

        // 只是用来签名
        payMent.remove("appId");
        return payMent;
    }

    /**
     * xml字符串解析
     *
     * @param xmlStr 字符串
     * @return 解析生成TreeMap对象
     */
    private Map<String, String> xmlStr2Map(String xmlStr) {
        // 参数为空或空字符串
        if (StringUtils.isEmpty(xmlStr)) {
            return Collections.emptyMap();
        }
        Map<String, String> map = new TreeMap<>(String::compareTo);
        Document doc;
        try {
            doc = DocumentHelper.parseText(xmlStr);
            Element root = doc.getRootElement();
            List children = root.elements();
            if (children != null && children.size() > 0) {
                for (int i = 0; i < children.size(); i++) {
                    Element child = (Element) children.get(i);
                    map.put(child.getName(), child.getTextTrim());
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }
}
