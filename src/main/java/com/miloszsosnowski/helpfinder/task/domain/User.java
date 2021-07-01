package com.miloszsosnowski.helpfinder.task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    String email;
    String password;
    Address addressOfUser;
    String phoneNumber;
}
