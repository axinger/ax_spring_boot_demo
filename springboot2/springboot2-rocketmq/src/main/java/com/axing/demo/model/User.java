package com.axing.demo.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private String name;
    private Integer age;
    private LocalDateTime dateTime = LocalDateTime.now();
}
