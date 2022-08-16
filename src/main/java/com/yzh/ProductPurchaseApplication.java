package com.yzh;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@MapperScan("com.yzh.mapper")
@Slf4j
public class ProductPurchaseApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ProductPurchaseApplication.class);
        ConfigurableEnvironment env = application.run(args).getEnvironment();
        log.info("success start!");
        log.info("knife4j API URL: \thttp://localhost:{}/doc.html",env.getProperty("server.port"));
    }

}
