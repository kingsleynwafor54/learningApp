package com.ileiwe.controller;


import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.data.model.Course;
import com.ileiwe.data.repository.CourseRepository;
import com.ileiwe.service.courseService.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
  @Autowired
  CourseService courseService;

    @PostMapping("/{id}")
    public ResponseEntity<?> createCourse(@RequestBody CourseDto courseDto, @PathVariable Long id) {

      return ResponseEntity.ok().body(courseService.create(courseDto, id));
    }
    @GetMapping("/course/{id}")
    public Course getCourse(@PathVariable Long id) {

      return courseService.viewCourse(id);
    }

  @PutMapping("/update/{id}/{num}")
  public Course update(@RequestBody CourseDto courseDto,@PathVariable Long id,@PathVariable Long num) {

     return courseService.update(courseDto,id,num);
  }
  @GetMapping("/allCourses")
public List<Course> viewAllCourses(){
      return courseService.viewAllCourse();
    }
}