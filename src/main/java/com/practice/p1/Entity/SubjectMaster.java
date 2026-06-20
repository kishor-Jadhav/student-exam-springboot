package com.practice.p1.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SubjectMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long subjectId;
    String subjectName;
    @OneToMany(mappedBy = "subjects")
    List<StudentExamMarks> studentExamMarks;
}
