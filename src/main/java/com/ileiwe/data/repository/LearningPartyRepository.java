package com.ileiwe.data.repository;

import com.ileiwe.data.model.LearningParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author oluwatobi
 * @version 1.0
 * @date on 28/10/2021
 * inside the package - com.ileiwe.data.repository
 */

public interface LearningPartyRepository extends JpaRepository<LearningParty, Long> {

    LearningParty findByEmail(String email);

//    @Query("select '*' from LearningParty" +
//            " as L where L.email =:email")
//    LearningParty findUserByEmail(String email);
}
