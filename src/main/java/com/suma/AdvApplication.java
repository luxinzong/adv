package com.suma;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.suma.dao")
public class AdvApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdvApplication.class, args);
    }
}
