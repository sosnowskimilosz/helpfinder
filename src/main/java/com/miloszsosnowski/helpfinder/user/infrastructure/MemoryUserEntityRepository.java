package com.miloszsosnowski.helpfinder.user.infrastructure;

import com.miloszsosnowski.helpfinder.user.domain.UserEntity;
import com.miloszsosnowski.helpfinder.user.domain.UserEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryUserEntityRepository implements UserEntityRepository {

    private final AtomicLong ID_NEXT_VALUE = new AtomicLong(0L);
    private final Map<Long, UserEntity> users = new HashMap<>();

    @Override
    public List<UserEntity> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void save(UserEntity userEntity) {
        if (userEntity.getId() != null) {
            users.put(userEntity.getId(), userEntity);
        } else {
            Long nextId = nextId();
            userEntity.setId(nextId);
            users.put(nextId, userEntity);
        }
    }

    private Long nextId() {
        return ID_NEXT_VALUE.getAndIncrement();
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public void removeById(Long id) {
        users.remove(id);
    }
}
