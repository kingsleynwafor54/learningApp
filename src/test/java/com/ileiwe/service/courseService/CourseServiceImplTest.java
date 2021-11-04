package com.ileiwe.service.courseService;

import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.model.Course;
import com.ileiwe.data.repository.CourseRepository;
import com.ileiwe.data.repository.InstructorRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    void update() {
    }

    @Test
    void testUpdate() {
    }

    @Test
    void delete() {
        int size = courseRepository.findAll().size();
        courseServiceImpl.delete(1L);
        assertThat(courseRepository.findAll().size()).isEqualTo(size-1);

    }

    @Test
    void viewCourse() {
    }

    @Test
    void publishCourse() {
    }
}