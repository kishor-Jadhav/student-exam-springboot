package com.practice.p1.Entity;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_master")
public class StudentMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    Long studentId;

    @Column(name = "student_name")
    String studentName;

    @Column(name = "student_no")
    Long studentNo;
    @ManyToOne(fetch = FetchType.EAGER) //When student load it will load class
    @JoinColumn(name="std_class_id")
    StudentClass studClass;

    @OneToMany(mappedBy = "students")
    List<StudentExamMarks> studentExamMarks;

    int year;

    
    @Override
    public String toString() {
        return "StudentMaster [studentId=" + studentId + ", studentName=" + studentName + "]";
    }
    

}
