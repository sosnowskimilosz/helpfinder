package com.miloszsosnowski.helpfinder.task.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.beans.Transient;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Task {

    private Long id;
    private String description;
    private Long userId;
    private Long addressId;
    private BigDecimal price;
    private TaskStatus taskStatus = TaskStatus.SUBMITTED;
//    private transient LocalDateTime dateOfCreated;
//    private transient LocalDateTime dateOfLastEdition;

    public Task(String description, Long userId, Long addressId, BigDecimal price) {
        this.description = description;
        this.userId = userId;
        this.addressId = addressId;
        this.price = price;
    }
}