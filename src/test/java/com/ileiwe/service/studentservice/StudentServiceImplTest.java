package com.ileiwe.service.studentservice;

import com.ileiwe.data.dto.StudentDto;
import com.ileiwe.data.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class StudentServiceImplTest {
@Autowired
    StudentRepository studentRepository;
@Autowired
StudentServiceImpl studentService;
    @BeforeEach
    void setUp() {
    }
    @Test
    @Transactional
    @Rollback(value = false)
    void testThatStudentCanBeCreated(){
        StudentDto studentDto=new StudentDto();
        studentDto.setEmail("ShedrackPelumi@gmail.com");
        studentDto.setPassword("1234");
        studentDto.setFirstname("Shedrack");
        studentDto.setLastname("Pelumi");
        studentDto.setDob(LocalDate.of(1992,9,19));
        int size=studentRepository.findAll().size();
        assertThat(studentRepository.findAll().size()).isEqualTo(size);
        studentService.createNewStudent(studentDto);
        assertThat(studentRepository.findAll().size()).isEqualTo(size+1);
        log.info("The size of student repository is : {}",studentRepository.findAll().size());
    }
    @Test
    void testToUpdateStudentDetail(){
        StudentDto studentDto=new StudentDto();
//        studentDto.setEmail("ShedrackPelumi@gmail.com");
//        studentDto.setPassword("1234");
        studentDto.setFirstname("ShedrackMeshack");
        studentDto.setLastname("Pete");
        int size=studentRepository.findAll().size();
        studentDto.setDob(LocalDate.of(1993,7,29));
        assertThat(studentRepository.findAll().size()).isEqualTo(size);
        studentService.updateStudent(studentDto,5L);
        assertThat(studentRepository.findAll().size()).isEqualTo(size);
    }
}