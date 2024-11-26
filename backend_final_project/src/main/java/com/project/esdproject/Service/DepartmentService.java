package com.project.esdproject.Service;

import com.project.esdproject.model.Department;
import com.project.esdproject.Repository.DepartmentRepository; // Assuming DepartmentDAO is replaced with Spring Data JPA Repository
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    public boolean addDepartment(Department department) {
        try {
            departmentRepository.save(department);
            return true;
        } catch (Exception e) {
            logger.error("Error in DepartmentService", e);
            return false;
        }
    }

    public List<Department> getDepartmentList() {
        return departmentRepository.findAll();
    }

    // If you have a login method in your DepartmentRepository, uncomment this.
    /*
    public Department login(Department dept) {
        return departmentRepository.login(dept);
    }
    */
}
