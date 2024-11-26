package com.project.esdproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @Column(name ="dept_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer departmentID;

    @Column(name="dept_name",nullable = false)
    private String departmentName;

    @Column(name="Capacity",nullable = false)
    private Integer capacity;
}
