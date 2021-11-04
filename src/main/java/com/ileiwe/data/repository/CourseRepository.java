package com.ileiwe.data.repository;

import com.ileiwe.data.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

/**
 * @author oluwatobi
 * @version 1.0
 * @date on 28/10/2021
 * inside the package - com.ileiwe.data.repository
 */
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findCourseByTitle(String title);

}
