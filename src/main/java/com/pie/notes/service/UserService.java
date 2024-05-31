package com.pie.notes.service;

import com.pie.notes.data.User;
import com.pie.notes.exception.userException.LoginException;
import com.pie.notes.exception.userException.RegisterExeception;

import java.util.List;

public interface UserService {
    boolean login(String name, String password) throws LoginException;
    User save(User user) throws RegisterExeception;
    List<User> findAll();
}
