package com.miloszsosnowski.helpfinder;

import com.miloszsosnowski.helpfinder.address.application.port.AddressUseCase;
import com.miloszsosnowski.helpfinder.address.application.port.AddressUseCase.CreateAddressCommand;
import com.miloszsosnowski.helpfinder.task.application.port.TaskUseCase;
import com.miloszsosnowski.helpfinder.address.domain.Address;
import com.miloszsosnowski.helpfinder.task.domain.Task;
import com.miloszsosnowski.helpfinder.user.application.port.UserUseCase;
import com.miloszsosnowski.helpfinder.user.application.port.UserUseCase.CreateUserCommand;
import com.miloszsosnowski.helpfinder.user.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.miloszsosnowski.helpfinder.task.application.port.TaskUseCase.*;
import static com.miloszsosnowski.helpfinder.task.application.port.TaskUseCase.CreateTaskCommand;

@Component
@AllArgsConstructor
public class ApplicationStartup implements CommandLineRunner {

    private final TaskUseCase taskService;
    private final AddressUseCase addressService;
    private final UserUseCase userService;

    @Override
    public void run(String... args) {
        initData();
        findAllTasksFromMemory();
        findAllAddressFromMemory();
        findAllUsersFromMemory();
        findTaskById(1L);
        findByAuthor(111L);
        findAndUpdate();
    }

    private void initData() {
        addressService.addAddress(new CreateAddressCommand("Ziemowita 11/7", "92-413", "Lodz"));
        addressService.addAddress(new CreateAddressCommand("Franciszka z Asyzu", "96-518", "Warszawa"));
        addressService.addAddress(new CreateAddressCommand("Zachodnia 23", "22-518", "Ksawerow"));

        userService.addUser(new CreateUserCommand("milo@wp.pl","pass123", 0L, "888-329-954"));
        userService.addUser(new CreateUserCommand("adam@wp.pl","haslo123", 1L, "535-999-954"));
        userService.addUser(new CreateUserCommand("pawel@wp.pl","lala123", 2L, "855-333-999"));


        taskService.addTask(new CreateTaskCommand("Mycia okien", 111L, 111L, BigDecimal.valueOf(50)));
        taskService.addTask(new CreateTaskCommand("Zrobienie zakupów", 222L, 222L, BigDecimal.valueOf(10)));
        taskService.addTask(new CreateTaskCommand("Wyprowadzanie psa", 333L, 333L, BigDecimal.valueOf(5)));
        taskService.addTask(new CreateTaskCommand("Mycie samochodu", 44L, 44L, BigDecimal.valueOf(20)));
    }

    private void findAllTasksFromMemory() {
        System.out.println("----- Find all tasks -----");
        List<Task> tasksInMemory = taskService.findAll();
        tasksInMemory.forEach(System.out::println);
    }

    private void findAllAddressFromMemory() {
        System.out.println("----- Find all addresses -----");
        List<Address> addressesInMemory = addressService.findAll();
        addressesInMemory.forEach(System.out::println);
    }

    private void findAllUsersFromMemory() {
        System.out.println("----- Find all users -----");
        List<User> usersInMemory = userService.findAll();
        usersInMemory.forEach(System.out::println);
    }

    private void findTaskById(Long id) {
        System.out.println("----- Find task by id -----");
        Optional<Task> task = taskService.findById(id);
        System.out.println(task.get());
    }

    private void findByAuthor(Long email) {
        System.out.println("----- Find task by author -----");
        List<Task> tasksInMemory = taskService.findByAuthor(email);
        tasksInMemory.forEach(System.out::println);
    }

    private void findAndUpdate() {
        System.out.println("----- Find and update -----");
        taskService.findById(1L)
                .ifPresent(task -> {
                    UpdateTaskCommand command = UpdateTaskCommand
                            .builder()
                            .id(task.getId())
                            .description("Obranie ziemniaków")
                            .price(BigDecimal.valueOf(25))
                            .build();

                    UpdateTaskResponse response = taskService.updateTask(command);
                    System.out.println("Updating task result: " + response);
                });
    }
}
