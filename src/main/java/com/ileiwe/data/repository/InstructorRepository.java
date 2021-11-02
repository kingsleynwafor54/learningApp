package com.ileiwe.data.repository;

import com.ileiwe.data.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author oluwatobi
 * @version 1.0
 * @date on 28/10/2021
 * inside the package - com.ileiwe.data.repository
 */
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

}
