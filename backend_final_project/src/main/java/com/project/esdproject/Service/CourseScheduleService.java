package com.project.esdproject.Service;

import com.project.esdproject.Repository.CourseScheduleRepository;
import com.project.esdproject.model.CourseSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseScheduleService {

    @Autowired
    private CourseScheduleRepository courseScheduleRepository;
    public List<CourseSchedule> getCourseSchedule(Integer emp_id) {
        System.out.println("Inside the Course schedule service with id  "+emp_id+"\n");
        return courseScheduleRepository.getcourseScheduleList(emp_id);
    }
}
