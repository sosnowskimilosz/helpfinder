package com.miloszsosnowski.helpfinder.user.domain;

import java.util.List;
import java.util.Optional;

public interface UserEntityRepository {

    List<UserEntity> findAll();

    void save(UserEntity userEntity);

    Optional<UserEntity> findById(Long id);

    void removeById(Long id);
}
