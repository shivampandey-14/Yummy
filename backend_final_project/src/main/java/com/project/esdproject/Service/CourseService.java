package com.project.esdproject.Service;


import com.project.esdproject.Repository.CourseRepository;
import com.project.esdproject.model.Courses;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    public boolean addCourse(Courses course){
        Courses crs = courseRepository.save(course);
        return crs.getCourse_code().equals(course.getCourse_code());
    }


    @Transactional
    public boolean addCoursesToId(Integer emp_id,Integer c_id){
        return courseRepository.updateEmployeeForCourse(emp_id, c_id) > 0;
    }


}
