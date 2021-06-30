package com.miloszsosnowski.helpfinder.task.application;

import com.miloszsosnowski.helpfinder.task.application.port.TaskUseCase;
import com.miloszsosnowski.helpfinder.task.domain.Task;
import com.miloszsosnowski.helpfinder.task.domain.User;
import com.miloszsosnowski.helpfinder.task.infrastructure.MemoryTaskRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService implements TaskUseCase {

    MemoryTaskRepository repository;

    @Override
    public List<Task> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Task> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Task> findByAuthor(User author) {
        return repository.findAll()
                .stream()
                .filter(task -> task.getAuthor().equals(author.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> findByCity(String city) {
        return repository.findAll()
                .stream()
                .filter(task -> task.getAddress().getCity().startsWith(city))
                .collect(Collectors.toList());
    }

    @Override
    public void addTask(CreateTaskCommand command) {
        Task task = command.toTask();
        repository.save(task);
    }

    @Override
    public UpdateTaskResponse updateTask(UpdateTaskCommand command) {
        return repository.findById(command.getId())
                .map(task -> {
                    Task updatedTask = command.updateFields(task);
                    repository.save(updatedTask);
                    return UpdateTaskResponse.SUCCESS;
                }).orElseGet(()-> new UpdateTaskResponse(false, Collections.singletonList("Task not found with id: " + command.getId())));
    }

    @Override
    public void removeById(Long id) {
        repository.removeById(id);
    }
}
