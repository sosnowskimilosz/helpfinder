package com.miloszsosnowski.helpfinder.task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    String street;
    String postalCode;
    String city;
}
