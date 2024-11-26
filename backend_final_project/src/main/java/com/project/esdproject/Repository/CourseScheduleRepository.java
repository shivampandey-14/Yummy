package com.project.esdproject.Repository;

import com.project.esdproject.model.CourseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CourseScheduleRepository extends JpaRepository<CourseSchedule, Integer> {

    @Query(value  = "select cs from CourseSchedule cs where cs.course_id.course_id in (select c.course_id from Courses c where c.employee_id.employee_id is null) and (cs.day, cs.time) not in (select cs2.day, cs2.time from CourseSchedule cs2 where cs2.course_id.course_id in (select co.course_id from Courses co where co.employee_id is not NULL and co.employee_id.employee_id = id))")
    List<CourseSchedule> getcourseScheduleList(Integer id);

}
