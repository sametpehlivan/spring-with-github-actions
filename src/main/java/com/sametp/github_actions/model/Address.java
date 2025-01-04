package com.sametp.github_actions.model;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document
public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;

}
