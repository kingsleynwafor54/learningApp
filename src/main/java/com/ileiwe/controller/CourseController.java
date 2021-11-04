package com.ileiwe.controller;


import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.data.model.Course;
import com.ileiwe.data.repository.CourseRepository;
import com.ileiwe.service.courseService.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Course createCourse(@PathVariable Long id) {

      return courseService.viewCourse(id);
    }

  @PutMapping("/update/{id}/{num}")
  public void update(@RequestBody CourseDto courseDto,@PathVariable Long id,@PathVariable Long num) {

     courseService.update(courseDto,id,num);
  }

    }
