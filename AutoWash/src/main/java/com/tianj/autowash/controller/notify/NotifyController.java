package com.tianj.autowash.controller.notify;

import com.tianj.autowash.constant.MsgType;
import com.tianj.autowash.constant.OrderStatus;
import com.tianj.autowash.controller.user.WechatController;
import com.tianj.autowash.entity.device.DataTransfer;
import com.tianj.autowash.entity.order.SysOrder;
import com.tianj.autowash.entity.user.User;
import com.tianj.autowash.service.device.BaseSocketService;
import com.tianj.autowash.service.order.OrderService;
import com.tianj.autowash.service.user.UserService;
import com.tianj.autowash.utils.DataTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
/**
 * 支付通知
 *
 * @author Zhangxq
 * @version v1.0
 * @update 2019-01-26 14:01
 */
@Controller
@RequestMapping("/notify")
@ApiIgnore
public class NotifyController {
    private static final Logger log = LoggerFactory.getLogger(WechatController.class);
    private final Marker marker = MarkerFactory.getDetachedMarker("NOTIFY");
    private static String res = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[%s]]></return_msg></xml>";
    @Autowired
    private BaseSocketService service;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


    /**
     * @param data
     * @param id   订单id
     * @return
     */
    @RequestMapping(value = "/weChat/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> weChatPayNotify(@RequestBody String data, @PathVariable("id") String id) throws Exception {
        SysOrder order = orderService.findById(id);
        // 解析微信回传xml信息
        Map<String, String> notifyResult = DataTools.xml2Map(data);
        // 回复微信结果
        String result;
        boolean unlock = false;
        // 判断订单是否已处理
        if (order != null) {
            if (!OrderStatus.ORDER_STATUS_COMPLETE.equals(order.getOrderResult())) {
                orderService.updateOrderInfo(notifyResult, order);
                String deviceId = notifyResult.get("device_info");
                HashMap<String, String> map = new HashMap<>(1);
                map.put("start", "true");
                unlock = service.postData(new DataTransfer(map, MsgType.TYPE_COMMAND), deviceId);
                if (order.getTradeType().equals("1")){
                    String cashFee = notifyResult.get("cash_fee");
                    String userId = notifyResult.get("attach");
                    User user = userService.findById(userId);
                    BigDecimal  balance= BigDecimal.valueOf(Long.valueOf(cashFee));
                    user.setBalance(user.getBalance().add(balance));
                    userService.insertOrUpdate(user);
                }
            }
            result = String.format(res, "OK");
        } else {
            result = String.format(res, "FAIL");
        }
        log.info(marker, "Pay Notify:{},is unLock:{}", notifyResult, unlock);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
