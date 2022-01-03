package com.miloszsosnowski.helpfinder.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserEntity {
    Long id;
    String email;
    String password;
    Long addressId;
    String phoneNumber;

    public UserEntity(String email, String password, Long addressId, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.addressId = addressId;
        this.phoneNumber = phoneNumber;
    }
}
