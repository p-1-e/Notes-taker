package com.pie.notes.exception.userException;

import java.text.MessageFormat;

public class LoginException extends UserExeption {

    public LoginException() {
        super("Invalid user name or password");
    }
}
