package com.pie.notes.exception.calculatorExeptions;

import org.springframework.jmx.export.naming.IdentityNamingStrategy;

public class InvalidOperationException extends CalculatorException{



    public InvalidOperationException(String message){
        super(message);
    }
}
