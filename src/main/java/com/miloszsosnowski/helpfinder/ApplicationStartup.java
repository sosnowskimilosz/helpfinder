package com.miloszsosnowski.helpfinder;

import com.miloszsosnowski.helpfinder.task.application.port.TaskUseCase;
import com.miloszsosnowski.helpfinder.task.domain.Address;
import com.miloszsosnowski.helpfinder.task.domain.Task;
import com.miloszsosnowski.helpfinder.task.domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.miloszsosnowski.helpfinder.task.application.port.TaskUseCase.*;
import static com.miloszsosnowski.helpfinder.task.application.port.TaskUseCase.CreateTaskCommand;

@Component
public class ApplicationStartup implements CommandLineRunner {

    private final TaskUseCase service;

    public ApplicationStartup(TaskUseCase service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {
        initData();
        findAllTasksFromMemory();
        findTaskById(1L);
        findByAuthor("milosz@gmail.com");
        findAndUpdate();
    }

    private void initData() {
        Address addressTestForMilosz = new Address("Ziemowita 11/7", "92-413", "Lodz");
        Address addressTestForAdam = new Address("Franciszka z Asyzu", "96-518", "Warszawa");
        User testUserMilosz = new User("milosz@gmail.com", "password123", addressTestForMilosz, "535-329-954");
        User testUserAdam = new User("adam@gmail.com", "pass321", addressTestForAdam, "543-123-432");
        service.addTask(new CreateTaskCommand("Mycia okien", testUserAdam, addressTestForAdam, BigDecimal.valueOf(50)));
        service.addTask(new CreateTaskCommand("Zrobienie zakupów", testUserAdam, addressTestForAdam, BigDecimal.valueOf(10)));
        service.addTask(new CreateTaskCommand("Wyprowadzanie psa", testUserMilosz, addressTestForMilosz, BigDecimal.valueOf(5)));
        service.addTask(new CreateTaskCommand("Mycie samochodu", testUserMilosz, addressTestForMilosz, BigDecimal.valueOf(20)));
    }

    private void findAllTasksFromMemory() {
        System.out.println("----- Find all tasks -----");
        List<Task> tasksInMemory = service.findAll();
        tasksInMemory.forEach(System.out::println);
    }

    private void findTaskById(Long id) {
        System.out.println("----- Find task by id -----");
        Optional<Task> task = service.findById(id);
        System.out.println(task.get());
    }

    private void findByAuthor(String email) {
        System.out.println("----- Find task by author -----");
        List<Task> tasksInMemory = service.findByAuthor(email);
        tasksInMemory.forEach(System.out::println);
    }

    private void findAndUpdate() {
        System.out.println("----- Find and update -----");
        service.findById(1L)
                .ifPresent(task -> {
                    UpdateTaskCommand command = UpdateTaskCommand
                            .builder()
                            .id(task.getId())
                            .description("Obranie ziemniaków")
                            .price(BigDecimal.valueOf(25))
                            .build();

                    UpdateTaskResponse response = service.updateTask(command);
                    System.out.println("Updating task result: " + response);
                });
    }
}
