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
public class CourseSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer course_schedule_id;

    @Column(nullable = false, name = "course_time")
    private String time;

    @Column(nullable = false, name = "course_day")
    private String day;

    @Column(nullable = false)
    private String room;

    @Column
    private String building;

    @JoinColumn(name="course_id")
    @ManyToOne(optional = true)
    private Courses course_id;


}

