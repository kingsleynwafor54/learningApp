package com.ileiwe.data.repository;

import com.ileiwe.data.model.Authority;
import com.ileiwe.data.model.LearningParty;
import com.ileiwe.data.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

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
@Sql(scripts = {"/db/insert.sql"})
class LearningPartyRepositoryTest {

    @Autowired
    LearningPartyRepository learningPartyRepository;

    @BeforeEach
    void setUp() {
    }
    @Test
    @Transactional
    @Rollback(value = false)
    void createLearningPartyWithStudentRoleTest(){
        LearningParty learningUser =
        new LearningParty("yomi@gmail.com",
                "Yomi123",
                new Authority(Role.ROLE_STUDENT));
        log.info("Before saving --> {}", learningUser);
       learningPartyRepository.save(learningUser);
       assertThat(learningUser.getId()).isNotNull();
       assertThat(learningUser.getEmail()).isEqualTo("yomi@gmail.com");
       assertThat(learningUser.getAuthorities()
               .get(0).getAuthority()).isEqualTo(Role.ROLE_STUDENT);
        assertThat(learningUser.getAuthorities()
                .get(0).getId()).isNotNull();

        log.info("After saving --> {}", learningUser);
    }

    @Test
    void createLearningPartyUniqueEmailsTest(){
        //create a learning party
        LearningParty user1 = new LearningParty("yomi@gmail.com",
                "Yomi123",
                new Authority(Role.ROLE_STUDENT));
        //save to db
        learningPartyRepository.save(user1);
        assertThat(user1.getEmail()).isEqualTo("yomi@gmail.com");
        assertThat(user1.getId()).isNotNull();
        //create another learning party with same email
        LearningParty user2 = new LearningParty("yomi@gmail.com",
                "Yomi123",
                new Authority(Role.ROLE_STUDENT));
        //save and catch exception
        assertThrows(DataIntegrityViolationException.class,
                () -> learningPartyRepository.save(user2));
    }

    @Test
    void learningPartyWithNullValuesTest(){
        //create a learning party with null values
        LearningParty user2 = new LearningParty(null,
                null,
                new Authority(Role.ROLE_STUDENT));
        //save and catch exception
        assertThrows(ConstraintViolationException.class,
                () -> learningPartyRepository.save(user2));

    }

    @Test
    void learningPartyWithEmptyStringValuesTest(){
        //create a learning party with null values
       LearningParty user = new LearningParty("  ",
                "",
                new Authority(Role.ROLE_STUDENT));
       assertThrows(ConstraintViolationException.class, ()-> learningPartyRepository.save(user));
    }

    @Test
    void findByUserNameTest(){
        LearningParty learningParty =
                learningPartyRepository.findByEmail("tomi@mail.com");
        assertThat(learningParty).isNotNull();
        assertThat(learningParty.getEmail()).isEqualTo("tomi@mail.com");
        log.info("Learning party object --> {}", learningParty);


    }



    @AfterEach
    void tearDown() {
    }
}