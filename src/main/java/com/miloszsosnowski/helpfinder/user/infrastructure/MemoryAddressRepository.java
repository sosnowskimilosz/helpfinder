package com.miloszsosnowski.helpfinder.user.infrastructure;

import com.miloszsosnowski.helpfinder.user.domain.Address;
import com.miloszsosnowski.helpfinder.user.domain.AddressRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryAddressRepository implements AddressRepository {

    private final AtomicLong ID_NEXT_VALUE = new AtomicLong(0L);
    private final Map<Long, Address> addresses = new HashMap<>();

    @Override
    public List<Address> findAll() {
        return new ArrayList<>(addresses.values());
    }

    @Override
    public void save(Address address) {
        if (address.getId() != null) {
            addresses.put(address.getId(), address);
        } else {
            long nextId = nextId();
            address.setId(nextId);
            addresses.put(nextId, address);
        }
    }

    @Override
    public Optional<Address> findById(Long id) {
        return Optional.ofNullable(addresses.get(id));
    }

    @Override
    public void removeById(Long id) {
        addresses.remove(id);
    }

    private long nextId() {
        return ID_NEXT_VALUE.getAndIncrement();
    }
}
