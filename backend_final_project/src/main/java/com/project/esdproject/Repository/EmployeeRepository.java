package com.project.esdproject.Repository;

import com.project.esdproject.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Integer> {
    // Additional query methods if necessary

    Optional<Employees> findByEmail(String email);
}
