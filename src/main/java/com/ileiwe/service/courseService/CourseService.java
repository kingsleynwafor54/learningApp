package com.ileiwe.service.courseService;

import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.model.Course;

public interface CourseService{
    Course create(CourseDto courseDto,Long id);
    Course update(CourseDto courseDto,Long id,Long num);
    void update(String title);
    void delete(Long id);
    Course viewCourse(Long id);
    void publishCourse(Long id);
    Course findCourseByTitle(String title);
}
