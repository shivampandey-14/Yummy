package com.project.esdproject.controller;

import com.project.esdproject.model.Employees;
import com.project.esdproject.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<Employees> addEmp(@RequestBody Employees employee) {
        System.out.println("\nInside the post mapping of register controller of employee : " + employee);

        if (employee.getEmpDepartment().getDepartmentID() != 2) {
            System.out.println("\nInside the post mapping first if\n");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Employees savedEmployee = employeeService.addEmp(employee);
        System.out.println("\nInside the post mapping employee add\n");

        if (savedEmployee != null) {
            System.out.println("\nInside the post mapping employee saved\n");
            return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
        } else {
            System.out.println("\nInside the post mapping employee Not saved\n");
            return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<Employees>> getAllEmployees() {
        System.out.println("\nInside get of employee controller  : ");
        List<Employees> emp = employeeService.getEmployeeList();
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }
}
