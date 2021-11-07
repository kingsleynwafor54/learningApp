package com.ileiwe.service.studentservice;

import com.ileiwe.data.dto.StudentDto;
import com.ileiwe.data.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentService  {

    Student createNewStudent(StudentDto studentDto);



}
