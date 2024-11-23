package com.scaler.productservice.dto;

import com.scaler.productservice.inheritancedemo.mappedsuperclass.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    public Long userId;
    public String name;

    public UserDTO(User user) {
        this.userId = user.getId();
        this.name = user.getName();
    }
}
