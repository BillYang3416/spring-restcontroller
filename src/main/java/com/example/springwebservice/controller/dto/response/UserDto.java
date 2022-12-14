package com.example.springwebservice.controller.dto.response;

import com.example.springwebservice.model.entity.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


    private String firstName;

    private String lastName;

    private int age;

    private String gender;

    private AddressDto address = new AddressDto();

    private List<AccountDto> accounts = new ArrayList<>();

    private List<RoleDto> roles = new ArrayList<>();


    public void convert(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.age = user.getAge();
        this.gender = user.getGender();
        if (user.getAddress() != null) {
            fillAddress(user);
        }
        if (user.getAccountList().size() != 0) {
            fillAccountList(user);
        }
        if (user.getRoleList().size() != 0) {
            fillRoleList(user);
        }

    }

    private void fillRoleList(User user) {
        user.getRoleList().forEach(role -> {
            RoleDto roleDto = new RoleDto();
            roleDto.setName(role.getName());
            this.roles.add(roleDto);
        });
    }

    private void fillAccountList(User user) {
        user.getAccountList().forEach(account -> {
            AccountDto accountRes = new AccountDto();
            accountRes.setAccNo(account.getAccNo());
            accountRes.setBalance(account.getBalance());
            this.accounts.add(accountRes);
        });
    }

    private void fillAddress(User user) {
        this.address.setCity(user.getAddress().getCity());
        this.address.setCountry(user.getAddress().getCountry());
        this.address.setState(user.getAddress().getState());
        this.address.setStreet(user.getAddress().getStreet());
        this.address.setZipCode(user.getAddress().getZipCode());
    }

}
