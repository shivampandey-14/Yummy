/*
@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin authenticateAdmin(String email, String password) {
        // Fetch admin by email
        Admin admin = adminRepository.findByEmail(email);

        // Validate password (you can use BCrypt or plain text comparison based on your setup)
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }
}
*/

package com.project.esdproject.Service;

import com.project.esdproject.Exceptions.AdminNotFoundException;
import com.project.esdproject.Exceptions.InvalidPasswordException;
import com.project.esdproject.Repository.AdminRepository;
import com.project.esdproject.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin authenticateAdmin(String email, String password) {
        // Fetch admin by email
        Admin admin = adminRepository.findByEmail(email);

        // Raise exception if admin is not found
        if (admin == null) {
            throw new AdminNotFoundException("Admin with email " + email + " not found.");
        }

        // Raise exception if password does not match
        if (!admin.getPassword().equals(password)) {
            throw new InvalidPasswordException("Invalid password for email " + email + ".");
        }

        return admin;
    }
}
