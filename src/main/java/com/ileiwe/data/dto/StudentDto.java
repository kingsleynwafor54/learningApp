package com.ileiwe.data.dto;

import com.ileiwe.data.model.Gender;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudentDto {
        private String email;
        private String password;
        private String firstname;
        private String lastname;
        private LocalDate dob;
        private Gender gender;
}
