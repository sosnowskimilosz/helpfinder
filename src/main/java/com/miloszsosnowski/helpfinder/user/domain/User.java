package com.miloszsosnowski.helpfinder.user.domain;

import com.miloszsosnowski.helpfinder.address.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    Long id;
    String email;
    String password;
    Long addressId;
    String phoneNumber;

    public User(String email, String password, Long addressId, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.addressId = addressId;
        this.phoneNumber = phoneNumber;
    }
}
