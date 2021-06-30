package com.miloszsosnowski.helpfinder.task.application;

import com.miloszsosnowski.helpfinder.task.application.port.TaskUseCase;
import com.miloszsosnowski.helpfinder.task.db.TaskJpaRepository;
import com.miloszsosnowski.helpfinder.task.domain.Task;
import com.miloszsosnowski.helpfinder.task.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements TaskUseCase {

    TaskJpaRepository repository;

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Task> findByAuthor(User author) {
        return null;
    }

    @Override
    public List<Task> findByCity(String city) {
        return null;
    }

    @Override
    public Task addTask(CreateTaskCommand command) {
        return null;
    }

    @Override
    public UpdateTaskResponse updateTask(UpdateTaskCommand command) {
        return null;
    }

    @Override
    public void removeById(Long id) {

    }
}
