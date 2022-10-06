package com.example.springwebservice.controller.dto.request;

import com.example.springwebservice.controller.dto.response.AccountDto;
import com.example.springwebservice.controller.dto.response.AddressDto;
import com.example.springwebservice.controller.dto.response.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    private String firstName;
    private String lastName;
    private int age;
    private String gender;

    private AddressDto address;

    private List<AccountDto> accounts;
    private List<RoleDto> roles;

}