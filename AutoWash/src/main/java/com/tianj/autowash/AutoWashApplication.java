package com.tianj.autowash;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Administrator
 * @version v1.0
 * @update 2018-12-20 10:37
 */

@SpringBootApplication(scanBasePackages = {"com.tianj.autowash"})
@EnableSwagger2
@MapperScan("com.tianj.autowash.dao")
@EnableCaching
@EnableTransactionManagement()
public class AutoWashApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutoWashApplication.class, args);
    }
}
