package com.miloszsosnowski.helpfinder.user.application;

import com.miloszsosnowski.helpfinder.user.application.port.UserUseCase;
import com.miloszsosnowski.helpfinder.user.domain.UserEntity;
import com.miloszsosnowski.helpfinder.user.infrastructure.MemoryUserEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserUseCase {

    MemoryUserEntityRepository repository;

    @Override
    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void addUser(CreateUserCommand command) {
        repository.save(command.toUser());
    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserCommand command) {
        return repository.findById(command.getId())
                .map(user -> {
                    UserEntity updatedUserEntity = command.updateUser(user);
                    repository.save(updatedUserEntity);
                    return UpdateUserResponse.SUCCESS;
                }).orElseGet(() -> new UpdateUserResponse(false, Collections.singletonList("User with id: " + command.getId() + "does not exist!")));
    }

    @Override
    public void removeById(Long id) {
        repository.removeById(id);
    }
}
