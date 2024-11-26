package com.project.esdproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer course_id;

    @Column(name = "course_code",nullable=false, unique = true)
    private String course_code;

    @Column(name = "course_name", nullable = false)
    private String name;

    @Column(name = "course_description")
    private String description;

    @Column(name = "course_year", nullable = false)
    private Integer year;

    @Column(name = "course_credits", nullable = false)
    private Integer credits;

    @Column(name = "course_term" , nullable = false)
    private Integer term;

    @Column(name = "course_capacity", nullable = false)
    private Integer capacity;

    @JoinColumn(name="employee_id")
    @ManyToOne(optional = true)
    private Employees employee_id;
}
