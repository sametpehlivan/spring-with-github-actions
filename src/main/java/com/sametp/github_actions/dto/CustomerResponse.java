package com.sametp.github_actions.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sametp.github_actions.model.Address;
import com.sametp.github_actions.model.Customer;

public record CustomerResponse(
    String id,
    @JsonProperty("first_name")
    String firstName,
    @JsonProperty("last_name")
    String lastName,
    String email,
    AddressResponse address
) {
    public static CustomerResponse from(Customer customer){
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                AddressResponse.from(customer.getAddress())
        );
    }
}
record AddressResponse(
    String street,
    @JsonProperty("house_number")
    String houseNumber,
    @JsonProperty("zip_code")
    String zipCode
){
    public static AddressResponse from(Address address){
        return new AddressResponse(
                address.getStreet(),
                address.getHouseNumber(),
                address.getHouseNumber()
        );
    }
}