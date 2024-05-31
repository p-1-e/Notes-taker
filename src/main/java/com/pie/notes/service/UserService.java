package com.pie.notes.service;

import com.pie.notes.data.User;
import java.util.List;

public interface UserService {
    boolean login(String name, String password);
    User save(User user);
    List<User> findAll();
}
