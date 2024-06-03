package com.pie.notes.controller;

import com.pie.notes.data.User;
import com.pie.notes.exception.userException.LoginException;
import com.pie.notes.exception.userException.RegisterExeception;
import com.pie.notes.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
   private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        try {
            userService.save(user);
            return ResponseEntity.ok(user);
        } catch (RegisterExeception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/login")
    public ResponseEntity<Boolean> login(@RequestParam String username, @RequestParam String password) {
        try {
            return ResponseEntity.ok(userService.login(username, password));
        } catch (LoginException e) {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @GetMapping("/users")
    public List<User> findAll(){
        return userService.findAll();
    }

}
