package com.miloszsosnowski.helpfinder.task.infrastructure;

import com.miloszsosnowski.helpfinder.task.domain.Task;
import com.miloszsosnowski.helpfinder.task.domain.TaskRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryTaskRepository implements TaskRepository {

    private final AtomicLong ID_NEXT_VALUE = new AtomicLong(0L);
    private final Map<Long, Task> tasks = new HashMap<>();

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void save(Task task) {
        if (task.getId() != null) {
            tasks.put(task.getId(), task);
        } else {
            long nextId = nextId();
            task.setId(nextId);
            tasks.put(nextId, task);
        }
    }

    @Override
    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    @Override
    public void removeById(Long id) {
        tasks.remove(id);
    }

    private long nextId() {
        return ID_NEXT_VALUE.getAndIncrement();
    }
}
