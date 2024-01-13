package com.axing.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ValidationControllerTest {

    @Autowired
    private ValidationController validationController;
    @Test
    void test1(){
        String test = validationController.test("jim");
        System.out.println("test = " + test);
    }
    @Test
    void test2(){
        String test = validationController.test(null);
        System.out.println("test = " + test);
    }

    @Test
    void test3(){
        String test = validationController.test("");
        System.out.println("test = " + test);
    }

    @Test
    void test4(){
        String test = validationController.test(" ");
        System.out.println("test = " + test);
    }
}
