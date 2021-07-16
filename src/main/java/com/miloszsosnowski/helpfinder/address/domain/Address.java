package com.miloszsosnowski.helpfinder.address.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    Long id;
    String street;
    String postalCode;
    String city;
//    private transient LocalDateTime dateOfCreated;
//    private transient LocalDateTime dateOfLastEdition;

    public Address(String street, String postalCode, String city) {
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }
}
