package com.sametp.github_actions.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sametp.github_actions.model.Address;
import com.sametp.github_actions.model.Customer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
    String id,
    @NotNull(message = "Customer Firstname Required")
    @JsonProperty("first_name")
    String firstName,
    @NotNull(message = "Customer Lastname Required")
    @JsonProperty("last_name")
    String lastName,
    @NotNull(message = "Customer Lastname Required")
    @Email(message = "Customer email is not a valid email")
    String email,
    @Valid
    AddressRequest address
) {
    public Customer to(){
        return Customer.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .address(address.to())
                .build();
    }
}
record AddressRequest(
    @NotNull(message = "Address Street Required")
    String street,
    @NotNull(message = "Address House Number Required")
    @JsonProperty("house_number")
    String houseNumber,
    @NotNull(message = "Address Zip Code Required")
    @JsonProperty("zip_code")
    String zipCode
){
    public Address to(){
        return Address.builder()
                .street(street)
                .houseNumber(houseNumber)
                .zipCode(zipCode)
                .build();
    }
}