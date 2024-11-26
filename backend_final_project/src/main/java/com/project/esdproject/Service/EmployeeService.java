package com.project.esdproject.Service;

import com.project.esdproject.model.Employees;
import com.project.esdproject.Repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Employees> getEmployeeList() {
        return employeeRepository.findAll();
    }

    @Transactional
    public Employees addEmp(Employees employee) {
        try {
            // Encrypt the password before persisting
            String encryptedPassword = passwordEncoder.encode(employee.getPassword());
            employee.setPassword(encryptedPassword);

            String binaryImageString = employee.getPhoto_path();
            String curr_email = employee.getEmail();
            employee.setPhoto_path(""); // Temporarily clear the photo path

            entityManager.persist(employee);

            TypedQuery<Employees> query = entityManager.createQuery("SELECT e FROM Employees e WHERE e.email = :curr_email", Employees.class);
            query.setParameter("curr_email", curr_email);
            employee = query.getSingleResult();

            String fileName = employee.getEmployee_id().toString() + ".txt";
            File file = new File(fileName);

            if (file.createNewFile()) {
                try (FileWriter fileWriter = new FileWriter(file)) {
                    fileWriter.write(binaryImageString);

                    employee.setPhoto_path(fileName);
                    entityManager.merge(employee);

                    return employee;
                } catch (IOException e) {
                    logger.error("Error writing to file", e);
                    return null;
                }
            } else {
                logger.warn("File already exists.");
                return null;
            }
        } catch (Exception e) {
            logger.error("Error while adding employee", e);
            return null;
        }
    }
}
