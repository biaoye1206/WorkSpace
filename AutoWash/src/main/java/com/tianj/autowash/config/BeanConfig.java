package com.tianj.autowash.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianj.autowash.service.device.BaseSocketService;
import com.tianj.autowash.service.device.DeviceService;
import com.tianj.autowash.service.device.SocketServiceImpl;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Bean对象
 *
 * @author zhangxq
 * @version v1.0
 * @update 2019-01-09 16:38
 */
@Configuration
public class BeanConfig {
    /**
     * 正常启用的http端口8008
     */
    @Value("${server.port}")
    private Integer httpPort;

    @Value("${socket.port}")
    private Integer port;


    /**
     * json Bean
     *
     * @return
     */
    @Bean
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ObjectMapper createJson() {
        return new ObjectMapper();
    }

    /**
     * 配置硬件连接服务并启动服务
     *
     * @param service
     * @return
     */
    @Bean(initMethod = "start")
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public BaseSocketService createSocketService(@Autowired DeviceService service) {
        return new SocketServiceImpl(port, service);
    }

    /**
     * SSL配置
     * springboot2 写法
     */
    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }

    /**
     * SSL配置
     */
    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        //Connector监听的http的端口号
        connector.setPort(8011);
        connector.setSecure(false);
        //监听到http的端口号后转向到的https的端口号
        connector.setRedirectPort(httpPort);
        return connector;
    }
}
