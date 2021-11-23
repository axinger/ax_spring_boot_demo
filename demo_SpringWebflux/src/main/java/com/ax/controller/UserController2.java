package com.ax.controller;


import com.ax.entity.User;
import com.ax.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController2 {
    /// flux 注解方法,和springmvc 类似
    @Autowired
    private UserService userService;


    @GetMapping("/user/{id}")
    public Mono<User> getUserById(@PathVariable Integer id) {

        return userService.getUserById(id);
    }

    // 查询所有用户 Flux 多个返回值
    @GetMapping("/user")
    public Flux<User> getAllUser() {
        return userService.getAllUser();
    }


    // 添加用户, 注意 参数类型 Mono<User>
    @PostMapping("/add")
    public Mono<Void> addUser(@RequestBody User user) {
        Mono<User> userMono = Mono.just(user);
        return userService.addUser(userMono);
    }
}
