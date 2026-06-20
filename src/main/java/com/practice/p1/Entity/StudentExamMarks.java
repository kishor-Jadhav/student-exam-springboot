package com.practice.p1.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StudentExamMarks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long studentExamMarksId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    StudentMaster students;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exam_id")
    ExamMaster examDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    SubjectMaster subjects;

    int marks;
}
