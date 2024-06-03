package com.pie.notes.exception.calculatorExeptions;

import java.text.MessageFormat;

public class InvalidOperatorException extends CalculatorException{
    public InvalidOperatorException(String operator){
       super(MessageFormat.format("Operator {0} is not valid", operator));
    }
}
