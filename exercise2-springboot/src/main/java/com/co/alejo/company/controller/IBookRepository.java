package com.co.alejo.company.controller;

import com.co.alejo.company.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Employee, Long> {
}
