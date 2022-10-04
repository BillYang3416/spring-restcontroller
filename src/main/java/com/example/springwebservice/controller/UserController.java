package com.example.springwebservice.controller;

import com.example.springwebservice.controller.dto.request.CreateUserRequest;
import com.example.springwebservice.controller.dto.request.UpdateUserRequest;
import com.example.springwebservice.controller.dto.response.QueryUserResponse;
import com.example.springwebservice.controller.dto.response.StatusResponse;
import com.example.springwebservice.model.entity.User;
import com.example.springwebservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(produces = {"application/json", "application/xml"})
    public List<QueryUserResponse> getAllUsers() {

        List<User> users = userService.getAllUsers();
        List<QueryUserResponse> queryUserResponses = new ArrayList<>();
        users.forEach(user -> {
            QueryUserResponse response = new QueryUserResponse();
            response.convert(user);
            queryUserResponses.add(response);
        });

        return queryUserResponses;
    }

    @GetMapping("/{id}")
    public QueryUserResponse getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        QueryUserResponse response = new QueryUserResponse();
        response.convert(user);
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
