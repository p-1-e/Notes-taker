package com.pie.notes.exception.calculatorExeptions;

public class CalculatorNotFoundException extends CalculatorException{
    public CalculatorNotFoundException() {
        super("Calculator not found");
    }
}
