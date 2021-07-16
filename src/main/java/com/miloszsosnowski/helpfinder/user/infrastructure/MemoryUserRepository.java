package com.miloszsosnowski.helpfinder.user.infrastructure;

import com.miloszsosnowski.helpfinder.user.domain.User;
import com.miloszsosnowski.helpfinder.user.domain.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryUserRepository implements UserRepository {

    private final AtomicLong ID_NEXT_VALUE = new AtomicLong(0L);
    private final Map<Long, User> users = new HashMap<>();

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void save(User user) {
        if (user.getId() != null) {
            users.put(user.getId(), user);
        } else {
            Long nextId = nextId();
            user.setId(nextId);
            users.put(nextId, user);
        }
    }

    private Long nextId() {
        return ID_NEXT_VALUE.incrementAndGet();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public void removeById(Long id) {
        users.remove(id);
    }
}
