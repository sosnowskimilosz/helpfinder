package com.miloszsosnowski.helpfinder.user.web;

import com.miloszsosnowski.helpfinder.user.application.port.UserUseCase;
import com.miloszsosnowski.helpfinder.user.domain.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    UserUseCase service;

    @ResponseStatus
    @GetMapping
    public List<UserEntity> getAll() {
        return service.findAll();
    }

}
