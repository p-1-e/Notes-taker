package com.pie.notes.repository;

import com.pie.notes.data.Calculator;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.pie.notes.data.User;

public interface CalculatorRepository extends JpaRepository<Calculator, Long> {
    List<Calculator> findAllByUser(User user);
}
