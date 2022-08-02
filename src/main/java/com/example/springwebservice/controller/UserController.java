package com.example.springwebservice.controller;

import com.example.springwebservice.model.User;
import com.example.springwebservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public List<User> getAllUsers() {
        return null;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return null;
    }

    @PostMapping()
    public User createUser(@RequestBody User user) {
        return null;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id,@RequestBody User user) {
        return null;
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable int id) {
        return null;
    }
} // Class end
