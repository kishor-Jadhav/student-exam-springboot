package com.practice.p1.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class ClassSubjectMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long classSubId;
    @ManyToOne()
    @JoinColumn(name = "std_class_id")
    StudentClass studClass;
    @ManyToOne()
    @JoinColumn(name = "subject_id")
    SubjectMaster subjects;


}
