package com.miloszsosnowski.helpfinder.user.application;

import com.miloszsosnowski.helpfinder.user.application.port.AddressUseCase;
import com.miloszsosnowski.helpfinder.user.domain.Address;
import com.miloszsosnowski.helpfinder.user.infrastructure.MemoryAddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressService implements AddressUseCase {

    private final MemoryAddressRepository repository;

    @Override
    public List<Address> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Address> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Address> findByCity(String city) {
        return repository.findAll()
                .stream()
                .filter(address -> address.getCity().equals(city))
                .collect(Collectors.toList());
    }

    @Override
    public void addAddress(CreateAddressCommand command) {
        Address address = command.toAddress();
        repository.save(address);
    }

    @Override
    public UpdateAddressResponse updateAddress(UpdateAddressCommand command) {
        return repository.findById(command.getId())
                .map(address -> {
                    Address updatedAddress = command.updateAddress(address);
                    repository.save(updatedAddress);
                    return UpdateAddressResponse.SUCCESS;
                }).orElseGet(() -> new UpdateAddressResponse(false, Collections.singletonList("Address with id: " + command.getId() + " does not exist!")));
    }

    @Override
    public void removeById(Long id) {
        repository.removeById(id);
    }
}
