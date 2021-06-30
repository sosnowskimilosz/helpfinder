package com.miloszsosnowski.helpfinder.task.domain;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    List<Task> findAll();

    void save(Task task);

    Optional<Task> findById(Long id);

    void removeById(Long id);
}
