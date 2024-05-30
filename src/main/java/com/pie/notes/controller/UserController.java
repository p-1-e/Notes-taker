package com.pie.notes.controller;

import com.pie.notes.data.User;
import com.pie.notes.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
   private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        userService.register(user);
        return user;
    }

    @GetMapping("/login")
    public boolean login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }

}
