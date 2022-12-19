package com.axing.demo.JsonMixin;

import com.axing.demo.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonMixin;

@JsonMixin(User.class) // 1
public abstract class FullNameMixin {
    @JsonProperty("fullName") // 2
    String name; // 3
}
