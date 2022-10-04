package com.example.springwebservice.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    private String firstName;
    private String lastName;
    private int age;
    private String gender;

    private Address address;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Address {
        private String city;

        private String country;

        private String state;

        private String street;

        private String zipCode;
    }
}