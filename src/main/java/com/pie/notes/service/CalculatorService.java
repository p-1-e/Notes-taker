package com.pie.notes.service;
import com.pie.notes.exception.calculatorExeptions.CalculatorNotFoundException;

import com.pie.notes.data.Calculator;
import com.pie.notes.exception.calculatorExeptions.InvalidOperationException;
import com.pie.notes.exception.calculatorExeptions.InvalidOperatorException;
import com.pie.notes.data.User;
import java.util.List;

public interface CalculatorService {
    List<Calculator> findAll(User user) throws CalculatorNotFoundException;

    Calculator basicOperation(double[] number, String operator, User user) throws InvalidOperationException, InvalidOperatorException;
    Calculator quadratic(double a, double b, double c, User user) throws InvalidOperationException;
    Calculator gcd(Integer a, Integer b, User user) throws InvalidOperationException;
    Calculator lcm(Integer a, Integer b, User user) throws InvalidOperationException;
}
