package com.practice.p1.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ExamMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long examId;
    String examName;
    int year;
    @OneToMany(mappedBy = "examDetails")
    List<StudentExamMarks> studentExamMarks;
}
