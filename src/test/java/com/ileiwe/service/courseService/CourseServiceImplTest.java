package com.ileiwe.service.courseService;

import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.model.Course;
import com.ileiwe.data.repository.CourseRepository;
import com.ileiwe.data.repository.InstructorRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class CourseServiceImplTest {
    @Autowired
InstructorRepository instructorRepository;
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseServiceImpl courseServiceImpl;
    @Test
    void create() {
        CourseDto courseDto=new CourseDto();
        courseDto.setTitle("Java");
        courseDto.setDescription("Learning programming using the language of java");
        courseDto.setDuration("1hour, 30min");
        courseDto.setLanguage("English");
       assertThat(courseRepository.findAll().size()).isEqualTo(1);
       courseServiceImpl.create(courseDto,1L);
        assertThat(courseRepository.findAll().size()).isEqualTo(2);
    }



    @Transactional
    @Rollback(value = true)
    @Test
    void delete() {
        //int size = courseRepository.findAll().size();
        //courseServiceImpl.delete(2L);
        assertThat(courseRepository.findAll().size()).isEqualTo(1);

    }

    @Test
    void viewCourse() {
        log.info("This are the Courses",courseServiceImpl.viewCourse(1L));
    }

    @Test
    void publishCourse() {
Course course1=courseRepository.findById(2L).get();
        //assertThat(course1.isPublished()).isFalse();
        log.info("The result of the test is before{}",course1.isPublished());
     Course course= courseServiceImpl.publishCourse(2L,1L);

//
       assertThat(course.isPublished()).isTrue();
        log.info("The result of the test is After{}",course.isPublished());
    }
//    @Test
//    void viewFindAll(){
//
//    }
}