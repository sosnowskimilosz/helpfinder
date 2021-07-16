package com.miloszsosnowski.helpfinder.task.application.port;

import com.miloszsosnowski.helpfinder.task.domain.Task;
import com.miloszsosnowski.helpfinder.task.domain.TaskStatus;
import lombok.Builder;
import lombok.Value;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

public interface TaskUseCase {

    List<Task> findAll();

    Optional<Task> findById(Long id);

    List<Task> findByAuthor(Long author);

    void addTask(CreateTaskCommand command);

    UpdateTaskResponse updateTask(UpdateTaskCommand command);

    void removeById(Long id);

    @Value
    class CreateTaskCommand {
        String description;
        Long userId;
        Long addressId;
        BigDecimal price;

        public Task toTask() {
            return new Task(description, userId, addressId, price);
        }
    }

    @Value
    @Builder
    class UpdateTaskCommand {
        Long id;
        String description;
        Long userId;
        Long addressId;
        BigDecimal price;
        TaskStatus taskStatus;

        public Task updateFields(Task task) {
            if (description != null) {
                task.setDescription(description);
            }
            if (userId != null) {
                task.setUserId(userId);
            }
            if (addressId != null) {
                task.setAddressId(addressId);
            }
            if (price != null) {
                task.setPrice(price);
            }
            if (taskStatus != null) {
                task.setTaskStatus(taskStatus);
            }
            return task;
        }
    }

    @Value
    class UpdateTaskResponse {
        public static UpdateTaskResponse SUCCESS = new UpdateTaskResponse(true, emptyList());

        boolean success;
        List<String> errors;
    }
}
