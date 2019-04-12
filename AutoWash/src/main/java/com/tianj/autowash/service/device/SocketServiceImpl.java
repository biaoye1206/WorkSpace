package com.tianj.autowash.service.device;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.tianj.autowash.basic.ContextMsgNotityEvent;
import com.tianj.autowash.constant.MsgType;
import com.tianj.autowash.entity.device.DataTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxq
 * @version v1.0
 * @update 2018-12-13 10:02
 */

public class SocketServiceImpl extends BaseSocketService {

    private static Logger logger = LoggerFactory.getLogger(SocketServiceImpl.class);
    private Integer port;
    private DeviceService service;
    private ServerSocket socket;

    @Autowired
    private ApplicationContext context;
    public SocketServiceImpl(Integer port, DeviceService service) {
        this.service = service;
        this.port = port;
    }

    /**
     * 监听客户进入
     */
    private void accept(int port) {
        try {
            // 创建服务器socket对象
            socket = new ServerSocket();
            // 绑定端口
            socket.bind(new InetSocketAddress(port));
            while (true) {
                // 监听用户连接
                Socket client = socket.accept();
                new GetClientData(client).start();
            }
        } catch (IOException e) {
            logger.warn("退出监听客户连接");
        }
    }

    @Override
    public void run() {
        accept(port);
    }

    @Override
    public void serverClose() throws IOException {
        socket.close();
    }

    /**
     * 客户数据读取私有类
     */
    private class GetClientData extends Thread {
        /**
         * 客户Socket
         */
        private Socket socket;
        /**
         * 客户输入流
         */
        private BufferedInputStream in;

        /**
         * 构造方法
         *
         * @param socket 客户socket对象
         * @throws IOException
         */
        public GetClientData(Socket socket) {
            try {
                this.socket = socket;
                this.in = getInput(socket);
            } catch (IOException e) {
                // 关闭连接，使用户重新连接
                try {
                    socket.close();
                } catch (IOException e1) {
                    logger.warn(e1.getLocalizedMessage());
                }
            }
        }

        /**
         * 获取输出流
         *
         * @param socket 客户对象
         * @return
         * @throws IOException
         */
        private PrintWriter getWriter(Socket socket) throws IOException {
            return new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
        }

        /**
         * 获取输入流
         *
         * @param socket 客户对象
         * @return
         * @throws IOException
         */
        private BufferedInputStream getInput(Socket socket) throws IOException {
            return new BufferedInputStream(socket.getInputStream());
        }

        /**
         * 重新线程方法，执行客户数据接收
         */
        @Override
        public void run() {
            byte[] buf = new byte[1024];
            String devId = null;
            try {
                int len = in.read(buf);
                devId = new String(buf, 0, len, StandardCharsets.UTF_8);
                Arrays.fill(buf, (byte) 0);
                PrintWriter writer = getWriter(this.socket);
                // 做安全校验判断是否为硬件用户，非注册硬件不处理
                if (service.findByDevId(devId) == null) {
                    // 非法连接设备 响应提示
                    writer.printf("Illegal user, refuse to connect");
                } else {
                    logger.info(devId + " 设备进入");
                    // 注册硬件设备
                    devRegister.put(devId, writer);
                    // 接收数据
                    while (true) {
                        len = in.read(buf, 0, 1024);
                        String msg = new String(buf, 0, len, StandardCharsets.UTF_8);
                        try {
                            DataTransfer interaction = json.readValue(msg, DataTransfer.class);
                            context.publishEvent(new ContextMsgNotityEvent(interaction));
                            // 返回状态
                            Map<String, String> data = new HashMap<String, String>(1) {{
                                put("msg", "ok");
                            }};
                            postData(new DataTransfer(data, MsgType.TYPE_REPLY), devId);

                        } catch (JsonProcessingException j) {
                            logger.warn("客户发送数据解析异常:" + j.getMessage());
                            HashMap<String, String> data = new HashMap<String, String>(1) {{
                                put("msg", "The parameter is incorrect");
                            }};
                            postData(new DataTransfer(data, MsgType.TYPE_ERROR), devId);
                        }
                        // 缓冲区归0
                        Arrays.fill(buf, (byte) 0);
                    }
                }
            } catch (Exception e) {
                logger.info(devId + "设备断开连接");
            } finally {
                if (devRegister.get(devId) != null) {
                    devRegister.remove(devId);
                }
                try {
                    socket.close();
                } catch (Exception e1) {
                    logger.warn(e1.getLocalizedMessage());
                }
            }
        }
    }
}
