package com.project.esdproject.Repository;

import com.project.esdproject.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Courses, Integer> {

    @Modifying
    @Query("update Courses c set c.employee_id.employee_id = :employeeId where c.course_id = :courseId")
    int updateEmployeeForCourse(Integer employeeId, Integer courseId);

}
