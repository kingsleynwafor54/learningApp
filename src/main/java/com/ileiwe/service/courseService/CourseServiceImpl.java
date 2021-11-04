package com.ileiwe.service.courseService;

import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.model.Course;
import com.ileiwe.data.model.Instructor;
import com.ileiwe.data.repository.CourseRepository;
import com.ileiwe.data.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    CourseRepository courseRepository;

    @Override
    @Transactional
    public Course create(CourseDto courseDto,Long id) {
      Instructor instructor=instructorRepository.findById(id).orElse(null);
      Course courseInRepo = courseRepository.findCourseByTitle(courseDto.getTitle());
      if(courseInRepo != null){
          throw new NullPointerException("Course with tile "+courseDto.getTitle() +" already exists");
      }

       if(instructor != null){
           Course course = new Course();
           course.setTitle(courseDto.getTitle());
           course.setDescription(courseDto.getDescription());
           course.setDuration(courseDto.getDuration());
           course.setLanguage(courseDto.getLanguage());
           course.setImageUrls(courseDto.getImgUrl());
           course.setInstructor(instructor);

            return courseRepository.save(course);
        }
       else{throw new NullPointerException("Instructor with id "+id +" not found");}

    }

//    @Override
//    public void update(CourseDto courseDto,Long id,Long num) {
//        Instructor instructor=instructorRepository.findById(id).get();
//        int num1 = Math.toIntExact(num);
//
//        instructor.getCourses().get(num1).setTitle(courseDto.getTitle());
//        instructor.getCourses().get(num1).setDescription(courseDto.getDescription());
//        instructor.getCourses().get(num1).setDuration(courseDto.getDuration());
//        instructor.getCourses().get(num1).setLanguage(courseDto.getLanguage());
//        instructor.getCourses().get(num1).setImageUrls(courseDto.getImgUrl());
//        instructor.getCourses().get(num1).setInstructor(courseDto.getInstructor());
//
//        courseRepository.save(instructor.getCourses().get(num1));
//    }

    @Override
    public Course update(CourseDto courseDto,Long id,Long num) {
        Course course = courseRepository.findById(id).orElse(null);
        if (instructorRepository.findById(id).isPresent()) {
            Instructor instructor = instructorRepository.findById(id).get();


            int num1 = Math.toIntExact(num);

            if (course != null) {
                course.setTitle(courseDto.getTitle());
                course.setDescription(courseDto.getDescription());
                course.setDuration(courseDto.getDuration());
                course.setLanguage(courseDto.getLanguage());
                course.setImageUrls(courseDto.getImgUrl());
                course.setInstructor(instructor);

                return courseRepository.save(course);

            } else {
                throw new NullPointerException("Instructor with id " + id + " not found");
            }

        }
        return courseRepository.save(course);
    }
    @Override
    public void update(String title) {

    }

    @Override
    public void delete(Long id) {
        Optional<Course> course=courseRepository.findById(id);
        course.ifPresent(value -> courseRepository.delete(value));
        throw new NullPointerException("Course with id "+id +" not found");

    }

    @Override
    public Course viewCourse(Long id) {

        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public void publishCourse(Long id) {

    }

    @Override
    public Course findCourseByTitle(String title) {
        List<Course> courses = courseRepository.findAll();
        for(Course course : courses){
            if(course.getTitle().equals(title)){
                return course;
            }
        }
        return null;
    }
}
