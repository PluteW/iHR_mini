package com.example.ihr_mini;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.example.ihr_mini")
@EnableTransactionManagement
public class IhrMiniApplication {

    public static void main(String[] args) {
        SpringApplication.run(IhrMiniApplication.class, args);
    }
}