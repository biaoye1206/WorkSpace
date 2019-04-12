package com.tianj.autowash.config;


import com.tianj.autowash.entity.user.User;
import com.tianj.autowash.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器配置
 *
 * @author zhangxq*
 * @version v1.0
 * @update 2019-01-19 13:04
 */
@Configuration
public class InterceptConfig extends WebMvcConfigurationSupport {

    @Autowired
    private UserService userService;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserIntercept())
                .addPathPatterns("/**")
                .addPathPatterns("/**/insert")
                .addPathPatterns("/**/update")
                .excludePathPatterns("/**/find*", "/wechat/*", "/notify/**","/**/error","/img/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**")
                .order(0);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/static/img/circuit/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    /**
     * 用户请求拦截对象
     */
    private class UserIntercept extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            String token = request.getHeader("token");
            if (!StringUtils.isEmpty(token)) {
                User user = userService.findUserBySessionId(token);
                if (user != null) {
                    request.setAttribute("user", user);
                    return true;
                }
            }
            throw new Exception("请先登陆");
        }
    }

}
