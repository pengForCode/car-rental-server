package com.changmu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author chang mu
 * @version 1.0
 * @date 2022/4/27
 * @description 租车启动类
 */
@SpringBootApplication
@MapperScan(basePackages = "com.changmu.db.mapper")
public class StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}
