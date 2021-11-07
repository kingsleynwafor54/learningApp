package com.ileiwe.controller;

import com.ileiwe.data.dto.StudentDto;
import com.ileiwe.data.model.Course;
import com.ileiwe.data.model.Student;
import com.ileiwe.service.studentservice.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController{
    @Autowired
    StudentService studentService;
    @PostMapping("/create_new_student")
    public Student createNewStudent(@RequestBody StudentDto studentDto){
        return studentService.createNewStudent(studentDto);
    }
    @PutMapping("/update_student/{id}")
    public Student updateStudentDetails(@RequestBody StudentDto studentDto,@PathVariable Long id){
    return studentService.updateStudent(studentDto,id);
    }
    @GetMapping("/get_all_published_courses")
    public List<Course> getAllPublishedCourses(){
        return studentService.checkPublishedCourse();
    }
}
