package com.project.esdproject.controller;

import com.project.esdproject.model.Department;
import com.project.esdproject.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // Working
    @PostMapping("/add")
    public ResponseEntity<String> addDepartment(@RequestBody Department dept) {
        System.out.println("\nInside the post mapping of department add: with the department as : "+dept);
        boolean val = departmentService.addDepartment(dept);
        if (val) {
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failure while adding department");
        }
    }

    //working
    @GetMapping("/get")
    public ResponseEntity<List<Department>> getAllDepartments() {
        System.out.println("\nInside the get mapping of department:  ");
        List<Department> depts = departmentService.getDepartmentList();
        return ResponseEntity.ok(depts);
    }

    // If you have a method to get employees by department, uncomment this and refactor as well.
    /*
    @GetMapping("/get_employees/{dept_id}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable("dept_id") int dId) {
        List<Employee> employees = departmentService.getEmployeeListInDepartment(dId);
        return ResponseEntity.ok(employees);
    }
    */
}
