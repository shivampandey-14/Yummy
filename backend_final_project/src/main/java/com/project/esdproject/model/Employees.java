package com.project.esdproject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employee_id;

    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String first_name;

    @Column
    private String last_name;

    @Column
    private String title;

    @Column(nullable = false)
    private String photo_path;

    @JoinColumn(name = "dept_id")
    @ManyToOne(optional = true)
    private Department empDepartment;


    public Object getId() {
        return employee_id;
    }
}
