package com.miloszsosnowski.helpfinder.task.application.port;

import com.miloszsosnowski.helpfinder.task.domain.Address;
import com.miloszsosnowski.helpfinder.task.domain.Task;
import com.miloszsosnowski.helpfinder.task.domain.TaskStatus;
import com.miloszsosnowski.helpfinder.task.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TaskUseCase {

    List<Task> findAll();

    Optional<Task> findById(Long id);

    List<Task> findByAuthor(User author);

    List<Task> findByCity(String city);

    Task addTask(CreateTaskCommand command);

    UpdateTaskResponse updateTask(UpdateTaskCommand command);

    void removeById(Long id);

    @Value
    class CreateTaskCommand {
        String description;
        User author;
        Address address;
        BigDecimal price;
        TaskStatus taskStatus;

        public Task toTask() {
            return new Task(description, author, address, price, taskStatus);
        }
    }

    @Value
    @Builder
    @AllArgsConstructor
    class UpdateTaskCommand {
        Long id;
        String description;
        User author;
        Address address;
        BigDecimal price;
        TaskStatus taskStatus;

        public Task updateFields(Task task){
            if(description != null){
                task.setDescription(description);
            }
            if(author != null){
                task.setAuthor(author);
            }
            if(address != null){
                task.setAddress(address);
            }
            if(price != null){
                task.setPrice(price);
            }
            if(taskStatus != null){
                task.setTaskStatus(taskStatus);
            }
            return task;
        }
    }

    @Value
    class UpdateTaskResponse {

    }
}