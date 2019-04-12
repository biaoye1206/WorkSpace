package com.tianj.autowash.utils;

import com.tianj.autowash.constant.ConstWeChat;
import com.tianj.autowash.basic.BasicServiceImpl;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.spec.AlgorithmParameterSpec;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * 微信数据工具包
 *
 * @author Administrator
 * @version v1.0
 * @update 2019-01-08 14:40
 */
public class DataTools {
    /**
     * 本机IP地址
     */
    private static String localHostAddress;

    protected final static Logger log = LoggerFactory.getLogger(BasicServiceImpl.class);

    /**
     * 同步产生数据新id
     *
     * @return id
     */
    public static synchronized String generateId() {
        return UUID.randomUUID()
                .toString()
                .replaceAll("-", "")
                .toLowerCase();
    }

    /**
     * 生成随机字符串
     *
     * @return 随机字符串
     */
    public static String nonceStr() {
        return UUID.randomUUID()
                .toString()
                .replaceAll("-", "")
                .toLowerCase();
    }

    /**
     * 创建一个当前时间的时间戳对象
     *
     * @return 时间戳对象
     */
    public static Timestamp newDate() {
        return new Timestamp(createTimestamp());
    }

    /**
     * 创建一个当前时间戳数字
     *
     * @return 时间戳数字
     */
    public static Long createTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 数据校验
     *
     * @param rawData    源数据
     * @param sessionKey 密钥
     * @param signature  签名
     * @return 与
     */
    public static boolean checkDigest(String rawData, String sessionKey, String signature) {
        // 调用 apache 的公共包进行 SHA1 加密处理
        String sha1 = DigestUtils.sha1Hex((rawData + sessionKey).getBytes());
        return sha1.equals(signature);
    }

    /**
     * 解密
     *
     * @param encryptedData
     * @param sessionKey
     * @param iv
     * @return
     */
    public static String decode(String encryptedData, String sessionKey, String iv) {
        byte[] encDatas = Base64.decodeBase64(encryptedData);
        byte[] keys = Base64.decodeBase64(sessionKey);
        byte[] ivs = Base64.decodeBase64(iv);
        return decrypt(keys, ivs, encDatas);
    }

    private static String decrypt(byte[] key, byte[] iv, byte[] encData) {
        String result = null;
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            result = new String(cipher.doFinal(encData), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 数据签名
     *
     * @param map 待签名数据（treeMap并实现排序）
     * @return 签名
     */
    public static String dataSign(Map<String, String> map) {
        StringBuffer buf = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            buf.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        // 签名
        return org.springframework.util.DigestUtils
                .md5DigestAsHex(buf.append("key=" + ConstWeChat.KEY)
                        .toString().getBytes()).toUpperCase();
    }

    /**
     * 获取本机IP地址
     *
     * @return 本机IP地址
     */
    public static String getLocalAddress() {
        // 如果已获取过直接返回
        if (StringUtils.isEmpty(localHostAddress)) {
            synchronized (DataTools.class) {
                if (StringUtils.isEmpty(localHostAddress)) {
                    try {
                        localHostAddress = InetAddress.getLocalHost().getHostAddress();
                    } catch (UnknownHostException e) {
                        log.error("获取本机地址出错", e);
                        throw new RuntimeException(e.getMessage());
                    }
                }
            }
        }
        return localHostAddress;
    }

    /**
     * 解析xml字符串到map
     *
     * @param xml 字符串
     * @return map
     */
    public static Map<String, String> xml2Map(String xml) throws DocumentException {
        Map<String, String> map = new TreeMap<>(String::compareTo);
        //将xml转为dom对象
        Document doc = DocumentHelper.parseText(xml);
        //获取根节点
        Element root = doc.getRootElement();
        //获取这个子节点里面的所有子元素，也可以element.elements("userList")指定获取子元素
        List<Element> elements = root.elements();

        //遍历子元素
        for (Object obj : elements) {
            root = (Element) obj;
            map.put(root.getName(), root.getTextTrim());
        }
        return map;
    }
}