package com.miloszsosnowski.helpfinder.task.web;

import com.miloszsosnowski.helpfinder.task.application.port.TaskUseCase;
import com.miloszsosnowski.helpfinder.task.domain.Task;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/task")
@RestController
@AllArgsConstructor
public class TaskController {

    private final TaskUseCase service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAll() {
        return service.findAll();
    }
}
