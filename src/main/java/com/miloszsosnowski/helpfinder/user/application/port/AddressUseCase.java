package com.miloszsosnowski.helpfinder.user.application.port;

import com.miloszsosnowski.helpfinder.user.domain.Address;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

public interface AddressUseCase {

    List<Address> findAll();

    Optional<Address> findById(Long id);

    List<Address> findByCity(String city);

    void addAddress(CreateAddressCommand command);

    UpdateAddressResponse updateAddress(UpdateAddressCommand command);

    void removeById(Long id);

    @Value
    class CreateAddressCommand {
        String street;
        String postalCode;
        String city;

        public Address toAddress() {
            return new Address(street, postalCode, city);
        }
    }

    @Value
    @Builder
    class UpdateAddressCommand {
        Long id;
        String street;
        String postalCode;
        String city;

        public Address updateAddress(Address address) {
            if (street != null) {
                address.setStreet(street);
            }
            if (postalCode != null) {
                address.setPostalCode(postalCode);
            }
            if (city != null) {
                address.setCity(city);
            }
            return address;
        }
    }

    @Value
    class UpdateAddressResponse {
        public static UpdateAddressResponse SUCCESS = new UpdateAddressResponse(true, emptyList());

        boolean success;
        List<String> errors;
    }
}
