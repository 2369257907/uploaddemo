package com.uploaddemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.uploaddemo.mapper")
public class UploaddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploaddemoApplication.class, args);
    }

}
