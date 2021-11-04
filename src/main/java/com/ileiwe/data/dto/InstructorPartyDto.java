package com.ileiwe.data.dto;

import lombok.Data;
import org.springframework.context.annotation.Bean;

/**
 * @author oluwatobi
 * @version 1.0
 * @date on 29/10/2021
 * inside the package - com.ileiwe.data
 */

@Data
public class InstructorPartyDto {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
}
