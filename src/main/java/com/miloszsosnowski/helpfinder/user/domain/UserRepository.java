package com.miloszsosnowski.helpfinder.user.domain;

import com.miloszsosnowski.helpfinder.task.domain.Task;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    void save(User user);

    Optional<User> findById(Long id);

    void removeById(Long id);
}
