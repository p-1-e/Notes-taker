package com.pie.notes.controller;

import com.pie.notes.data.Calculator;
import com.pie.notes.exception.calculatorExeptions.CalculatorNotFoundException;
import com.pie.notes.exception.calculatorExeptions.InvalidOperationException;
import com.pie.notes.exception.calculatorExeptions.InvalidOperatorException;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.pie.notes.service.CalculatorService;
import org.springframework.web.bind.annotation.*;
import com.pie.notes.data.User;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService){
        this.calculatorService = calculatorService;
    }

    @GetMapping("/calculators")
    public ResponseEntity<List<Calculator>> findAll(@RequestBody User user){
        try {
            return ResponseEntity.ok().body(calculatorService.findAll(user));
        } catch (CalculatorNotFoundException e) {
            return ResponseEntity.badRequest().body(List.of());
        }
    }

    @PostMapping("/calculator")
    public ResponseEntity<Calculator> basicOperation(@RequestParam double[] numbers, @RequestParam String operator, @RequestBody User user){
        try{
            return ResponseEntity.ok().body(calculatorService.basicOperation(numbers, operator,user));
        }catch (InvalidOperatorException| InvalidOperationException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/quadratic")
    public ResponseEntity<Calculator> quadraticEquation(@RequestParam Double a, @RequestParam Double b, @RequestParam Double c, @RequestBody User user){
        try {
            return ResponseEntity.ok().body(calculatorService.quadratic(a, b, c, user));
        } catch (InvalidOperationException e){
             return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/gcd")
    public ResponseEntity <Calculator> gcd(@RequestParam Integer a, @RequestParam Integer b, @RequestBody User user){
        try{
            return ResponseEntity.ok().body(calculatorService.gcd(a, b, user));
        }catch (InvalidOperationException e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    @PostMapping("/lcm")
    public ResponseEntity<Calculator> lcm(@RequestParam Integer a, @RequestParam Integer b, @RequestBody User user){
        try{
            return ResponseEntity.ok().body(calculatorService.lcm(a, b, user));
        } catch (InvalidOperationException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

}
