package com.example.springwebservice.service;

import com.example.springwebservice.controller.dto.request.CreateUserRequest;
import com.example.springwebservice.controller.dto.request.UpdateUserRequest;
import com.example.springwebservice.model.UserRepository;
import com.example.springwebservice.model.entity.Account;
import com.example.springwebservice.model.entity.Address;
import com.example.springwebservice.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> userList;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        List<User> response = userRepository.findAll();

        return response;
    }

    public User getUser(Long id) {
        User response = userRepository.findById(id).get();

        return response;
    }

    // 新增一個 user 的資料
    public String createUser(CreateUserRequest request) {

        User user = getRequestUser(request);

        // 儲存進 DB
        userRepository.save(user);

        // 回傳 OK 告訴 Controller 完成儲存
        return "OK";
    }

    private User getRequestUser(CreateUserRequest request) {
        // 新增一個空的 user 的 entity = 新增一筆空的資料
        User user = new User();

        // 塞好資料：user 裡的資料是從 request 來的
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setAge(request.getAge());
        user.setGender(request.getGender());
        user.setAddress(getRequestAddress(request, user));
        user.setAccountList(getRequestAccounts(request, user));
        return user;
    }

    private User getRequestUser(User user, UpdateUserRequest request) {
        // 塞好資料：user 裡的資料是從 request 來的
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setAge(request.getAge());
        user.setGender(request.getGender());
        user.setAddress(getRequestAddress(request, user));
        user.setAccountList(getRequestAccounts(request, user));
        return user;
    }


    private List<Account> getRequestAccounts(CreateUserRequest request, User user) {
        List<Account> accountList = new ArrayList<>();
        request.getAccounts().forEach(account -> {
            Account newAccount = new Account();
            newAccount.setUser(user);
            newAccount.setAccNo(account.getAccNo());
            newAccount.setBalance(account.getBalance());
            accountList.add(newAccount);
        });
        return accountList;
    }

    private List<Account> getRequestAccounts(UpdateUserRequest request, User user) {
        List<Account> accountList = user.getAccountList();

        // orphanRemoval = true wiil delete no connection between user and account
        accountList.forEach(a -> a.setUser(null));
        accountList.clear();
        request.getAccounts().forEach(account -> {

            Optional<Account> accountOptional = accountList.stream().filter(a -> a.getAccNo().equals(account.getAccNo())).findAny();
            accountOptional.ifPresentOrElse((value) -> {
                Account updatedAccount = accountOptional.get();
                updatedAccount.setUser(user);
                updatedAccount.setAccNo(account.getAccNo());
                updatedAccount.setBalance(account.getBalance());
            }, () -> {
                Account newAccount = new Account();
                newAccount.setUser(user);
                newAccount.setAccNo(account.getAccNo());
                newAccount.setBalance(account.getBalance());
                accountList.add(newAccount);
            });

        });
        return accountList;
    }


    private Address getRequestAddress(CreateUserRequest request, User user) {
        Address address = new Address();
        address.setUser(user);
        address.setCity(request.getAddress().getCity());
        address.setCountry(request.getAddress().getCountry());
        address.setState(request.getAddress().getState());
        address.setStreet(request.getAddress().getStreet());
        address.setZipCode(request.getAddress().getZipCode());
        return address;
    }

    private Address getRequestAddress(UpdateUserRequest request, User user) {
        Address address = user.getAddress();
        address.setCity(request.getAddress().getCity());
        address.setCountry(request.getAddress().getCountry());
        address.setState(request.getAddress().getState());
        address.setStreet(request.getAddress().getStreet());
        address.setZipCode(request.getAddress().getZipCode());
        return address;
    }

    public String updateUser(Long id, UpdateUserRequest request) {
        // 確認 DB 中有沒有這筆資料
        User user = userRepository.findById(id).get();

        if (user == null) {
            return "FAIL";
        }

        // 將要更改的值塞進去
        user = getRequestUser(user, request);

        // 儲存進 DB
        userRepository.save(user);

        // 回傳 OK 告訴 Controller 完成儲存
        return "OK";
    }

    public String deleteUserById(Long id) {
        User user = userRepository.findById(id).get();

        if (user == null) {
            return "FAIL";
        }

        userRepository.deleteById(id);

        return "OK";
    }

} // Class end
