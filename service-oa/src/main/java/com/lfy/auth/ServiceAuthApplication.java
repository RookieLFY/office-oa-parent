package com.lfy.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lfy") //因为SpringBoot启动的时候有一个包的扫描规则，它只扫描当前包及其子包，但是我的配置类可能与这不一样，所以需要加上@ComponentScan
public class ServiceAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class, args);
    }
}
