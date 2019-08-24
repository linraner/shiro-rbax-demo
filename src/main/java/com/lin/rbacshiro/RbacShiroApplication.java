package com.lin.rbacshiro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RbacShiroApplication {
    private static final Logger logger = LoggerFactory.getLogger(RbacShiroApplication.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication();
        application.setBannerMode(Banner.Mode.OFF);
        application.run(RbacShiroApplication.class, args);
        logger.info("app start");
    }

}
