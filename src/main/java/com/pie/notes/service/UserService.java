package com.pie.notes.service;

import com.pie.notes.data.User;

public interface UserService {
    boolean login(String name, String password);
    User register(User user);

}
