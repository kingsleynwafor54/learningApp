package com.ileiwe.service.courseService;

import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.model.Course;
import com.ileiwe.data.model.Instructor;
import com.ileiwe.data.repository.CourseRepository;
import com.ileiwe.data.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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
//     Course courseInRepo = courseRepository.findCourseByTitle(courseDto.getTitle());
       Course course = new Course();
//
//          //
//
//if(courseInRepo==null){
//    throw new NullPointerException("Course with title " + courseDto.getTitle() + " is null");
//}
//          if (instructor != null) {

              course.setTitle(courseDto.getTitle());
              course.setDescription(courseDto.getDescription());
              course.setDuration(courseDto.getDuration());
              course.setLanguage(courseDto.getLanguage());
              course.setImageUrls(courseDto.getImgUrl());
              course.setInstructor(instructor);


              return courseRepository.save(course) ;


          }


      // else{throw new IllegalArgumentException("Already existing course "+id +" not found");}




    @Override
    public Course update(CourseDto courseDto,Long id,Long num) {
        Course course = courseRepository.findById(num).orElse(null);
        if (instructorRepository.findById(id).isPresent()) {
            Instructor instructor = instructorRepository.findById(id).get();

//            int num1 = Math.toIntExact(num);


            assert course != null;
            course.setTitle(courseDto.getTitle());
                course.setDescription(courseDto.getDescription());
                course.setDuration(courseDto.getDuration());
                course.setLanguage(courseDto.getLanguage());
                course.setImageUrls(courseDto.getImgUrl());
                course.setInstructor(instructor);

//              return courseRepository.save(course);


            } else {
                throw new NullPointerException("Instructor with id " + id + " not found");
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

        Course course= courseRepository.findById(id).orElse(null);
        if(course==null){
            throw new NullPointerException("Course does not exist");
        }
        return course;
    }

    @Override
    public Course publishCourse(Long courseId,Long instructorId) {
        Course course=courseRepository.findById(courseId).orElse(null);
        Instructor instructor=instructorRepository.findById(instructorId).orElse(null);
    if(course==null&& course.getInstructor().getId().equals(instructor.getId())){
        throw new NullPointerException("course does not exist");
    }

    if(!course.isPublished()){
        course.setPublished(true);
        course.setDatePublished(LocalDateTime.now());
    }
   else if(course.isPublished()){
        course.setPublished(false);
        course.setDatePublished(LocalDateTime.now());
    }

    return courseRepository.save(course);
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

    @Override
    public List<Course> viewAllCourse() {
        return courseRepository.findAll();
    }
    @Override
    public Instructor coursesAnInstructorHas(Long id) {
        Instructor instructor=instructorRepository.findById(id).orElse(null);
         Course course=courseRepository.findById(id).orElse(null);
       assert course != null;
        assert instructor != null;
        if(course.getInstructor().getId().equals(instructor.getId())){
           //int i = Math.toIntExact(id);
                instructor.getCourses().add(courseRepository.getById(id));

            //
        }
        return instructorRepository.save(instructor);
    }

}
