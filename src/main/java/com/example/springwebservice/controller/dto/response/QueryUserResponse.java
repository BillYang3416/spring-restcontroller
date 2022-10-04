package com.example.springwebservice.controller.dto.response;

import com.example.springwebservice.model.entity.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryUserResponse {


    private String firstName;

    private String lastName;

    private int age;

    private String gender;

    private Address address = new Address();

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

    public void convert(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.age = user.getAge();
        this.gender = user.getGender();
        if (user.getAddress() != null) {
            this.address.setCity(user.getAddress().getCity());
            this.address.setCountry(user.getAddress().getCountry());
            this.address.setState(user.getAddress().getState());
            this.address.setStreet(user.getAddress().getStreet());
            this.address.setZipCode(user.getAddress().getZipCode());
        }

    }

}
