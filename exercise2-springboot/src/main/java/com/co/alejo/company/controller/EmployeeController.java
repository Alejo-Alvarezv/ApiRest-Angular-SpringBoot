package com.co.alejo.company.controller;

import com.co.alejo.company.exceptions.EmployeeNotFoundException;
import com.co.alejo.company.model.Employee;
import com.co.alejo.company.model.IEmployeeRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EmployeeController {

    private final IEmployeeRepository iEmployeeRepository;

    EmployeeController(IEmployeeRepository repository){
        this.iEmployeeRepository = repository;
    }

    // find All Employees
    @GetMapping("/employees")
    public List<Employee> all() {
        return iEmployeeRepository.findAll();
    }

    // Create new employee
    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee){
        return iEmployeeRepository.save(newEmployee);
    }

    // find one employee
    @GetMapping("/employees/{id}")
    EntityModel <Employee> findById(@PathVariable Long id){

        Employee employee = iEmployeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return EntityModel.of(employee, //
                linkTo(methodOn(EmployeeController.class).findById(id)).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    }

    // Update or create a employee
    @PutMapping("/employees/{id}")
    Employee updateEmployee (@RequestBody Employee newEmployee, @PathVariable Long id){
        return iEmployeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return iEmployeeRepository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return iEmployeeRepository.save(newEmployee);
                });
    }

    // Delete an employee
    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id){
        iEmployeeRepository.deleteById(id);
    }
}
