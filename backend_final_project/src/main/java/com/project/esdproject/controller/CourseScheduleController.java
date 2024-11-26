package com.project.esdproject.controller;

import com.project.esdproject.model.CourseSchedule;
import com.project.esdproject.Service.CourseScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courseSchedule") // Base path for this controller
public class CourseScheduleController {
 //System.out.println();
    @Autowired
    private CourseScheduleService courseScheduleService;

    // working
    @GetMapping("/{emp_id}/get")
    public ResponseEntity<List<CourseSchedule>> getCourseSchedule(@PathVariable("emp_id") Integer id) {
        System.out.println("\nInside get mapping of course shecdule controller with empid : "+ id+"\n");
        List<CourseSchedule> courseSchedule = courseScheduleService.getCourseSchedule(id);

        System.out.println("Course scheduler : "+ courseSchedule+"\n\n");
        return ResponseEntity.ok().body(courseSchedule);
    }
}
