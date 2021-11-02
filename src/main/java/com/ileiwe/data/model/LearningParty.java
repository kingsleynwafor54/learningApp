package com.ileiwe.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author oluwatobi
 * @version 1.0
 * @date on 27/10/2021
 * inside the package - com.ileiwe.data.model
 */

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LearningParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    @NotBlank @NotNull
    private String email;
    @Column(nullable = false)
    @NotBlank @NotNull
    private String password;
    private boolean enabled;
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Authority> authorities;

    public LearningParty
            (String email, String password, Authority authority){

        this.email = email;
        this.password = password;
        addAuthority(authority);
        this.enabled = false;

    }
    public void addAuthority(Authority authority){
        if(this.authorities == null){
            this.authorities = new ArrayList<>();
        }
        this.authorities.add(authority);
    }

}


















