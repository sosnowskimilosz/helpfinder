package com.miloszsosnowski.helpfinder.user.application.port;

import com.miloszsosnowski.helpfinder.user.domain.UserEntity;
import lombok.Value;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

public interface UserUseCase {

    List<UserEntity> findAll();

    Optional<UserEntity> findById(Long id);

    void addUser(CreateUserCommand command);

    UpdateUserResponse updateUser(UpdateUserCommand command);

    void removeById(Long id);

    @Value
    class CreateUserCommand {
        String email;
        String password;
        Long addressId;
        String phoneNumber;

        public UserEntity toUser() {
            return new UserEntity(email, password, addressId, phoneNumber);
        }
    }

    @Value
    class UpdateUserCommand {
        Long id;
        String email;
        String password;
        Long addressId;
        String phoneNumber;

        public UserEntity updateUser(UserEntity userEntity) {
            if (email != null) {
                userEntity.setEmail(email);
            }
            if (password != null) {
                userEntity.setPassword(password);
            }
            if (addressId != null) {
                userEntity.setAddressId(addressId);
            }
            if (phoneNumber != null) {
                userEntity.setPhoneNumber(phoneNumber);
            }
            return userEntity;
        }
    }

    @Value
    class UpdateUserResponse {
        public static UpdateUserResponse SUCCESS = new UpdateUserResponse(true, emptyList());

        boolean success;
        List<String> errors;
    }
}

