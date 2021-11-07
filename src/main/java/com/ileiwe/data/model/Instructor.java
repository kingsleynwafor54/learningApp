package com.ileiwe.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;



@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull     @NotBlank
    @Column(nullable = false)
    private String firstname;
    @NotNull     @NotBlank
    @Column(nullable = false)
    private String lastname;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String specialization;
    @Column(length = 1000)
    private String bio;
    @OneToOne(cascade = CascadeType.PERSIST)
    private LearningParty learningParty;
    @OneToMany(cascade=CascadeType.PERSIST)
    private List<Course> courses;

//    public void addCourse(Course course){
//        if(courses==null){
//            courses=new ArrayList<>();
//        }
//        courses.add(course);
//    }

}
