package com.pie.notes.exception.userException;

import com.pie.notes.data.User;

import java.text.MessageFormat;

public class RegisterExeception extends UserExeption{
    public RegisterExeception(User user) {
        super(MessageFormat.format("User {0} not was found created", user));
    }
}
