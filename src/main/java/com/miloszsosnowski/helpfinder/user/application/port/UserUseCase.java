package com.miloszsosnowski.helpfinder.user.application.port;

import com.miloszsosnowski.helpfinder.user.domain.User;
import lombok.Value;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

public interface UserUseCase {

    List<User> findAll();

    Optional<User> findById(Long id);

    void addUser(CreateUserCommand command);

    UpdateUserResponse updateUser(UpdateUserCommand command);

    void removeById(Long id);

    @Value
    class CreateUserCommand {
        String email;
        String password;
        Long addressId;
        String phoneNumber;

        public User toUser() {
            return new User(email, password, addressId, phoneNumber);
        }
    }

    @Value
    class UpdateUserCommand {
        Long id;
        String email;
        String password;
        Long addressId;
        String phoneNumber;

        public User updateUser(User user) {
            if (email != null) {
                user.setEmail(email);
            }
            if (password != null) {
                user.setPassword(password);
            }
            if (addressId != null) {
                user.setAddressId(addressId);
            }
            if (phoneNumber != null) {
                user.setPhoneNumber(phoneNumber);
            }
            return user;
        }
    }

    @Value
    class UpdateUserResponse {
        public static UpdateUserResponse SUCCESS = new UpdateUserResponse(true, emptyList());

        boolean success;
        List<String> errors;
    }
}

