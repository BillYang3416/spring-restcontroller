package com.example.springwebservice.service;

import com.example.springwebservice.controller.dto.request.CreateUserRequest;
import com.example.springwebservice.controller.dto.request.UpdateUserRequest;
import com.example.springwebservice.model.UserRepository;
import com.example.springwebservice.model.entity.Address;
import com.example.springwebservice.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

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

        User user = getUser(request);

        // 儲存進 DB
        userRepository.save(user);

        // 回傳 OK 告訴 Controller 完成儲存
        return "OK";
    }

    private User getUser(CreateUserRequest request) {
        // 新增一個空的 user 的 entity = 新增一筆空的資料
        User user = new User();

        // 塞好資料：user 裡的資料是從 request 來的
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setAge(request.getAge());
        user.setGender(request.getGender());
        user.setAddress(getAddress(request, user));
        return user;
    }

    private User getUser(User user, UpdateUserRequest request) {
        // 塞好資料：user 裡的資料是從 request 來的
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setAge(request.getAge());
        user.setGender(request.getGender());
        user.setAddress(getAddress(request, user));
        return user;
    }


    private Address getAddress(CreateUserRequest request, User user) {
        Address address = new Address();
        address.setUser(user);
        address.setCity(request.getAddress().getCity());
        address.setCountry(request.getAddress().getCountry());
        address.setState(request.getAddress().getState());
        address.setStreet(request.getAddress().getStreet());
        address.setZipCode(request.getAddress().getZipCode());
        return address;
    }

    private Address getAddress(UpdateUserRequest request, User user) {
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
        user = getUser(user, request);

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
