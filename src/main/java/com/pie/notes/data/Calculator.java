package com.pie.notes.data;

import jakarta.persistence.*;

import java.util.List;
import com.pie.notes.data.User;
import org.springframework.lang.NonNull;

@Table
@Entity
public class Calculator {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(nullable = false)
    private List<Double> numbers;

    @Column
    private String operator;

    @Column
    private String result;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Calculator (List<Double> numbers, String operator, String result, User user) {
        this.numbers = numbers;
        this.operator = operator;
        this.result = result;
        this.user = user;
    }

    public Calculator(){}

    public List<Double> getNumber() {
        return numbers;
    }

    public void setNumber(List<Double> number) {
        this.numbers = number;
    }



    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

