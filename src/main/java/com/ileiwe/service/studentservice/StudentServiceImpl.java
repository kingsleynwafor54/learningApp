package com.ileiwe.service.studentservice;

import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.data.dto.StudentDto;
import com.ileiwe.data.model.*;
import com.ileiwe.data.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
@Autowired
    StudentRepository studentRepository;

    @Override
    public Student createNewStudent(StudentDto studentDto) {

        if(studentDto == null){
            throw new IllegalArgumentException("Instructor cannot be null");
        }
        LearningParty learningParty
                = new LearningParty(studentDto.getEmail()
//                            ,passwordEncoder.encode(instructorDto.getPassword())
                ,studentDto.getPassword()
                , new Authority(Role.ROLE_STUDENT));

        Student student= Student.builder()
                .lastname(studentDto.getLastname())
                .firstname(studentDto.getFirstname())
                .learningParty(learningParty).build();

        return studentRepository.save(student);
    }


}
