package com.ileiwe.service.instructorservice;

import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.data.dto.StudentDto;
import com.ileiwe.data.model.Instructor;
import com.ileiwe.data.model.Student;

/**
 * @author oluwatobi
 * @version 1.0
 * @date on 29/10/2021
 * inside the package - com.ileiwe.service.instructor
 */
public interface InstructorService {
    Instructor save(InstructorPartyDto dto);
   // Student create(StudentDto dto);

//     Instructor findInstructor()

}
