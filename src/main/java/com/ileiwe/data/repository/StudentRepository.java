package com.ileiwe.data.repository;

import com.ileiwe.data.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author oluwatobi
 * @version 1.0
 * @date on 28/10/2021
 * inside the package - com.ileiwe.data.repository
 */
public interface StudentRepository extends JpaRepository<Student,Long> {
}
