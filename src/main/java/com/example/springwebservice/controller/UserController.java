package com.example.springwebservice.controller;

import com.example.springwebservice.controller.dto.request.CreateUserRequest;
import com.example.springwebservice.controller.dto.request.UpdateUserRequest;
import com.example.springwebservice.controller.dto.response.StatusResponse;
import com.example.springwebservice.model.entity.User;
import com.example.springwebservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(produces = {"application/json", "application/xml"})
    public List<User> getAllUsers() {

        List<User> response = userService.getAllUsers();

        return response;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        User response = userService.getUser(id);
        return response;
    }

    @PostMapping()
    public StatusResponse createUser(@RequestBody CreateUserRequest request) {
        String response = userService.createUser(request);

        return new StatusResponse(response);
    }

    @PutMapping("/{id}")
    public StatusResponse updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request) {

        String response = userService.updateUser(id, request);

        return new StatusResponse(response);
    }

    @DeleteMapping("/{id}")
    public StatusResponse deleteUser(@PathVariable Long id) {
        String response = userService.deleteUserById(id);

        return new StatusResponse(response);
    }
} // Class end
