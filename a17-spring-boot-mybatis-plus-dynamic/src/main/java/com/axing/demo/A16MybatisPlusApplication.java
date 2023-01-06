package com.axing.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @MapperScan(value = {"com.axing.demo.mapper", "com.axing.demo.db2.mapper","com.axing.demo.db3.mapper"})
// @MapperScan(value = {"com.axing.demo.mapper","com.axing.demo.*.mapper"})
@MapperScan(value = {"com.axing.demo.*.mapper"})
public class A16MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(A16MybatisPlusApplication.class, args);
    }

}
