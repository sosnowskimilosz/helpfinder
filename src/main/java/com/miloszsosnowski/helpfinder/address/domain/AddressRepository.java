package com.miloszsosnowski.helpfinder.address.domain;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {

    List<Address> findAll();

    void save(Address address);

    Optional<Address> findById(Long id);

    void removeById(Long id);
}
