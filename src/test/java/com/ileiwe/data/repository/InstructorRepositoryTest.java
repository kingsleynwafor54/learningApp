package com.ileiwe.data.repository;

import com.ileiwe.data.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author oluwatobi
 * @version 1.0
 * @date on 28/10/2021
 * inside the package - com.ileiwe.data.repository
 */
@SpringBootTest
@Slf4j
@Sql(scripts=("/db/insert.sql"))
class InstructorRepositoryTest {

    @Autowired
    InstructorRepository instructorRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void saveInstructorAsLearningPartyTest(){
        //create a learning party
        LearningParty user =
                new LearningParty("trainer@ileiwe.com",
                        "1234pass", new Authority(Role.ROLE_INSTRUCTOR));
        //create instructor
        Instructor instructor = Instructor.builder()
                .firstname("John")
                .lastname("Alao")
                .learningParty(user).build();
        //save instructor
        log.info("Instructor before saving --> {}", instructor);
        instructorRepository.save(instructor);
        assertThat(instructor.getId()).isNotNull();
        assertThat(instructor.getLearningParty().getId()).isNotNull();
        log.info("Instructor after saving --> {}", instructor);

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void updateInstructorTableAfterCreate(){
        //create a learning party
        LearningParty user =
                new LearningParty("trainer@ileiwe.com",
                        "1234pass", new Authority(Role.ROLE_INSTRUCTOR));
        //create instructor
        Instructor instructor = Instructor.builder()
                .firstname("John")
                .lastname("Alao")
                .learningParty(user).build();
        //save instructor
        log.info("Instructor before saving --> {}", instructor);
        instructorRepository.save(instructor);
        assertThat(instructor.getId()).isNotNull();
        assertThat(instructor.getLearningParty().getId()).isNotNull();
        log.info("Instructor after saving --> {}", instructor);

        Instructor savedInstructor =
                instructorRepository.findById(instructor.getId()).orElse(null);
        log.info("Saved Instructor --> {}", savedInstructor);
        assertThat(savedInstructor).isNotNull();
        assertThat(savedInstructor.getBio()).isNull();
        assertThat(savedInstructor.getGender()).isNull();
        savedInstructor.setBio("I am java instructor");
        savedInstructor.setGender(Gender.MALE);
        instructorRepository.save(savedInstructor);
        assertThat(savedInstructor.getBio()).isNotNull();
        assertThat(savedInstructor.getGender()).isNotNull();


    }

    @Test
    void createInstructorWithNullValuesTest(){
        //create a learning party
        LearningParty user =
                new LearningParty("trainer@ileiwe.com",
                        "1234pass", new Authority(Role.ROLE_INSTRUCTOR));

        Instructor instructor = Instructor.builder()
                .firstname(null)
                .lastname(null)
                .learningParty(user).build();

        assertThrows(ConstraintViolationException.class
        ,() -> instructorRepository.save(instructor));

    }

    @Test
    void createInstructorWithEmptyValuesTest(){
        //create a learning party
        LearningParty user =
                new LearningParty("trainer@ileiwe.com",
                        "1234pass", new Authority(Role.ROLE_INSTRUCTOR));

        Instructor instructor = Instructor.builder()
                .firstname("")
                .lastname(" ")
                .learningParty(user).build();

        assertThrows(ConstraintViolationException.class
                ,() -> instructorRepository.save(instructor));

    }











}