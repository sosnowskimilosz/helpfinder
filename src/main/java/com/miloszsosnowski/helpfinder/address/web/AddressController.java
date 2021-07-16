package com.miloszsosnowski.helpfinder.address.web;

import com.miloszsosnowski.helpfinder.address.application.port.AddressUseCase;
import com.miloszsosnowski.helpfinder.address.domain.Address;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class AddressController {

    AddressUseCase service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Address> getAll() {
        return service.findAll();
    }
}
