package com.ileiwe.service.studentservice;

import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.data.dto.StudentDto;
import com.ileiwe.data.model.*;
import com.ileiwe.data.repository.CourseRepository;
import com.ileiwe.data.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Override
    public Student createNewStudent(StudentDto studentDto) {

        if (studentDto == null) {
            throw new IllegalArgumentException("Instructor cannot be null");
        }
        LearningParty learningParty
                = new LearningParty(studentDto.getEmail()
//                            ,passwordEncoder.encode(instructorDto.getPassword())
                , studentDto.getPassword()
                , new Authority(Role.ROLE_STUDENT));

        Student student = Student.builder()
                .lastname(studentDto.getLastname())
                .firstname(studentDto.getFirstname())
                .dob(studentDto.getDob())
                .learningParty(learningParty).build();

        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(StudentDto studentDto, Long id) {

        Student student = studentRepository.findById(id).orElse(null);
        if (studentRepository.findById(id).isPresent()) {

            assert student != null;
            student.setDob(studentDto.getDob());
            student.setFirstname(studentDto.getFirstname());
            student.setLastname(studentDto.getLastname());
            student.setGender(studentDto.getGender());

        } else {
            throw new NullPointerException("Student with id " + id + " not found");
        }


        return studentRepository.save(student);
    }

    @Override
    public List<Course> checkPublishedCourse() {
        List<Course> courses=new ArrayList<>();
        for(Course course:courseRepository.findAll()){
            if(course.isPublished()){
               courses.add(course);
            }
        }
   return courses;
    }

    @Override
    public Student enrollCourse(Long id, Long counter) {
        Course course = courseRepository.findById(counter).orElse(null);
        Student student = studentRepository.findById(id).orElse(null);
        if(course==null && student==null){
            throw new NullPointerException("Nothing dey there");
        }
        assert course != null;
        if (course.isPublished()) {
            assert student != null;
            student.getEnrolledCourses().add(course);
        }

        assert student != null;
        return studentRepository.save(student);
    }
}

