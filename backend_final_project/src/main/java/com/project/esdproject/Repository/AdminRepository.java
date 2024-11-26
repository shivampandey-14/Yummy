package com.project.esdproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.esdproject.model.Admin;


public interface AdminRepository extends JpaRepository<Admin, Integer> {

    // Method to find an admin by email and role
    Admin findByEmail(String email);
}




