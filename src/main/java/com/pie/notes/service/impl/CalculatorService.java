package com.pie.notes.service.impl;

import com.pie.notes.exception.calculatorExeptions.InvalidOperationException;
import com.pie.notes.exception.calculatorExeptions.InvalidOperatorException;
import com.pie.notes.exception.calculatorExeptions.CalculatorNotFoundException;
import java.util.ArrayList;
import java.util.List;
import com.pie.notes.data.Calculator;
import com.pie.notes.repository.CalculatorRepository;
import org.springframework.stereotype.Service;
import java.text.MessageFormat;
import com.pie.notes.data.User;

@Service
public class CalculatorService implements com.pie.notes.service.CalculatorService {
    private final CalculatorRepository calculatorRepository; 
    
    public CalculatorService(CalculatorRepository calculatorRepository){
        this.calculatorRepository = calculatorRepository;
    }
    private Calculator saveCalculator(List<Double> numbers, String operator, String result, User user ){
        var calculator = new Calculator(numbers, operator, result, user);
        return calculatorRepository.save(calculator);
    }
    
    @Override
    public List<Calculator> findAll(User user) throws CalculatorNotFoundException {
        var calculators = calculatorRepository.findAllByUser(user);
        if (calculators.isEmpty()){
            throw new CalculatorNotFoundException();
        }
        return calculators;
    }

    
    @Override
    public Calculator basicOperation(List<Double> numbers, String operator, User user) throws InvalidOperationException {
        double result = 0;
        if (numbers.size() != 2){
            throw new InvalidOperationException("Syntax Error");
        }
        switch (operator){
            case "+" -> result = numbers.get(0) + numbers.get(1);
            case "-" -> result = numbers.get(0) - numbers.get(1);
            case "*" -> result = numbers.get(0) * numbers.get(1);
            case "/" -> {
                if (numbers.get(1) == 0) {
                    throw new InvalidOperationException("Can not divide by zero");
                }
                result = numbers.get(0) / numbers.get(1);
            }
        }
        return saveCalculator(numbers, operator, MessageFormat.format("{0}", result), user);

    }

    @Override
    public Calculator quadratic(double a, double b, double c, User user) throws InvalidOperationException {
        double discriminate = Math.pow(b,2) - (4 * a * c);
        List<Double> roots;
        if(discriminate>=0){
            if (discriminate == 0){
                var x = (-b + Math.sqrt(discriminate)) / (2 * a);
                roots =  List.of(x);
            } else {
                var x1 = (-b + Math.sqrt(discriminate)) / (2 * a);
                var x2 = (-b - Math.sqrt(discriminate)) / (2 * a);
                roots = List.of(x1, x2);
            }
        } else {
            throw new InvalidOperationException("The result is a number complex");
        }
        return saveCalculator(List.of(a, b, c), "quadratic equation", roots.toString(), user);
    }

    @Override
    public Calculator gcd(Integer a, Integer b, User user) throws InvalidOperationException{
        List<Double> numbers = List.of((double) a,  (double) b);
        while(b != 0){
            int temp = b;
            b %= a;
            a = temp;
        }
        return saveCalculator(numbers, "gcd", a.toString(), user);
    }

    @Override
    public Calculator lcm(Integer a, Integer b, User user) throws InvalidOperationException {
        List<Double> numbers = List.of((double) a ,(double) b); 
        Integer result = (a*b)/ Integer.parseInt(gcd(a, b, user).getResult()) ;
        return saveCalculator(numbers, "lcm", result.toString(), user);
    }
}