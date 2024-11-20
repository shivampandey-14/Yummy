package com.shivam.esd_assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "customer")
    public class Customer {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column(name = "first_name", nullable = false)
        private String firstName;

        @Column(name = "last_name")
        private String lastName;

        @Column(name = "email", unique = true, nullable = false)
        private String email;

        @Column(name="password", nullable = false)
        private String password;

//        @Column(name = "access_token", unique = true)
//        private String accessToken;
    }

