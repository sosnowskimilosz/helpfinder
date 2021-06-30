package com.miloszsosnowski.helpfinder.task.domain;

import lombok.Data;

@Data
public class User {
    String email;
    String password;
    Address addressOfUser;
    String phoneNumber;
}
