package com.pie.notes.service;

import com.pie.notes.data.Calculator;
import com.pie.notes.data.User;
import com.pie.notes.exception.calculatorExeptions.InvalidOperationException;
import com.pie.notes.exception.calculatorExeptions.InvalidOperatorException;
import com.pie.notes.exception.userException.RegisterExeception;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CalculatorServiceTest {
    private final Logger log = LogManager.getLogger(CalculatorServiceTest.class);


    @Autowired
    private CalculatorService calculatorService;
    private  User user;
    @Autowired
    private  UserService userService;


    public void settingUser() throws RegisterExeception {
        user = new User("Pedro","123");
        userService.save(user);
    }

    @Test
    void addiction_of_the_two_numbers() throws InvalidOperatorException, InvalidOperationException, RegisterExeception {
        settingUser();
        double[] numbers = {2.0, 5.0};
        var operator = "+";
        var addiction = calculatorService.basicOperation(numbers,operator,user);
        log.info(addiction.getResult());
        assertEquals("7", addiction.getResult());
    }
    @Test
    void subtraction_of_the_two_numbers() throws InvalidOperatorException, InvalidOperationException, RegisterExeception {
        settingUser();
        double[] numbers= {2.0, 6.0};
        var operator = "-";
        var subtraction = calculatorService.basicOperation(numbers,operator,user);
        log.info(subtraction.getResult());
        assertEquals("-4", subtraction.getResult());
    }
    @Test
    void multiplication_of_two_numbers_user() throws InvalidOperationException, InvalidOperatorException, RegisterExeception {
        settingUser();
        double[] numbers = {2.0, 6.0};
        var operator = "*";
        var multiplication = calculatorService.basicOperation(numbers, operator, user);
        log.info(multiplication.getResult());
        assertEquals("12", multiplication.getResult());
    }
    @Test
    void division_of_two_numbers() throws InvalidOperatorException, InvalidOperationException, RegisterExeception {
        settingUser();
        double[] numbers= {6.0, 6.0};
        var operator = "/";
        var division = calculatorService.basicOperation(numbers, operator, user);
        log.info(division.getResult());
        assertEquals("1", division.getResult());
    }
    @Test
    void solve_a_second_degree_polynomial() throws InvalidOperationException, RegisterExeception {
        settingUser();
        double a = 1;
        double b = 3;
        double c = 2;
        var roots = calculatorService.quadratic(a,b,c,user);
        log.info(roots.getResult());
        assertEquals("[-1.0, -2.0]", roots.getResult());

    }
    @Test
    void find_the_greatest_common_divisor_between_two_numbers() throws InvalidOperationException, RegisterExeception {
        settingUser();
        Integer a = 48;
        Integer b = 18;
        var result = calculatorService.gcd(a, b, user);
        log.info(result.getResult());
        assertEquals("6", result.getResult());
    }
    @Test
    void find_the_least_common_multiple_between_two_numbers() throws InvalidOperationException, RegisterExeception {
        settingUser();
        Integer a = 6;
        Integer b = 8;
        var result = calculatorService.lcm(a, b, user);
        log.info(result.getResult());
        assertEquals("24", result.getResult());
    }
}
