package com.miloszsosnowski.helpfinder.task.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Task {

    private Long id;
    private String description;
    private transient User author;
    private transient Address address;
    private BigDecimal price;
    private TaskStatus taskStatus = TaskStatus.SUBMITTED;
    private LocalDateTime dateOfCreated;
    private LocalDateTime dateOfLastEdition;

    public Task(String description, User author, Address address, BigDecimal price) {
        this.description = description;
        this.author = author;
        this.address = address;
        this.price = price;
    }
}