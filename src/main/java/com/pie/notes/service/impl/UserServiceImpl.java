package com.pie.notes.service.impl;

import com.pie.notes.data.User;
import com.pie.notes.exception.userException.LoginException;
import com.pie.notes.exception.userException.RegisterExeception;
import com.pie.notes.repository.UserRepository;
import com.pie.notes.service.UserService;
import jakarta.persistence.PersistenceException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(String name, String password) throws LoginException {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getUserName().equals(name) && user.getPassword().equals(password)) {
                return true;
            }
        }
        throw new LoginException();
    }

    @Override
    public User save(User user) throws RegisterExeception {
        try {
            userRepository.save(user);
            return user;
        }catch (PersistenceException e){
            throw new RegisterExeception(user);

        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
