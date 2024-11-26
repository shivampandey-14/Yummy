package com.project.esdproject.controller;


import com.project.esdproject.model.Admin;
import com.project.esdproject.model.JwtResponse;
import com.project.esdproject.Security.JwtUtils;
import com.project.esdproject.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// working
@RestController
@RequestMapping("/api/admin")
public class AdminAuthController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin) {

        System.out.println("\n\n\n\n*****admin Request Body:  \n"+admin.getEmail()+"\n"+admin.getPassword()+"\n\n"+"Passing to authenticate\n");

        Admin authenticatedAdmin = adminService.authenticateAdmin(admin.getEmail(), admin.getPassword());

        System.out.println("\nAuthentication process completed\n");

        if (authenticatedAdmin != null) {
            System.out.println("\nAuthenticate admin is not null\n");

            String token = jwtUtils.generateToken(authenticatedAdmin);
            System.out.println("\nToken Generated:\n"+token);
            return ResponseEntity.ok().body(new JwtResponse(token));

        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}






