package com.axing.controller;


import com.axing.entity.User;
import com.axing.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/mvc")
public class UserController {
    /// flux 注解方法,和springmvc 类似
    @Autowired
    private UserService userService;


    @GetMapping("/user/{id}")
    public Mono<User> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @SneakyThrows
    @GetMapping("/sleep/{id}")
    public Object sleep(@PathVariable long id) {
        TimeUnit.SECONDS.sleep(id);
        return Map.of("time", id);
    }


    // 查询所有用户 Flux 多个返回值
    @GetMapping("/user")
    public Flux<Object> getAllUser() {
        return userService.getAllUser();
    }


    // 添加用户, 注意 参数类型 Mono<User>
    @PostMapping("/add")
    public Mono<Void> addUser(@RequestBody User user) {
        Mono<User> userMono = Mono.just(user);
        return userService.addUser(userMono);
    }


    // @GetMapping(path = "/em",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @GetMapping(path = "/em", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public ResponseBodyEmitter emitter() {

        ResponseBodyEmitter emitter = new ResponseBodyEmitter(-1L);

        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                emitter.send("jim");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                emitter.send("jim");
                emitter.complete();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return emitter;
    }


    @GetMapping(path = "/em2", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public ResponseBodyEmitter emitter2() {

        ResponseBodyEmitter emitter = new ResponseBodyEmitter(-1L);
        try {
            emitter.send("Hello World", MediaType.TEXT_PLAIN); // 5
            Thread.sleep(1000);
            emitter.send(User.builder().id(1).build(), MediaType.APPLICATION_JSON);
            Thread.sleep(1000);
            // emitter.send(User.builder().id(2).build(), MediaType.APPLICATION_XML);
            emitter.send(User.builder().id(2).build(), MediaType.APPLICATION_JSON);
            Thread.sleep(1000);
            // emitter.send(User.builder().id(3).build(), new MediaType("application", "another-person"));
            emitter.send(User.builder().id(3).build(), MediaType.APPLICATION_JSON);
            emitter.complete();;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return emitter;
    }

    @GetMapping("/em3")
    public ResponseEntity<StreamingResponseBody> handleRbe() {
        StreamingResponseBody stream = out -> {
            String msg = "/srb" + " @ " + new Date();
            out.write(msg.getBytes());
        };
        return new ResponseEntity(stream, HttpStatus.OK);
    }
}
