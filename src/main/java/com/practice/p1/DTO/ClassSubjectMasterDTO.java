package com.practice.p1.DTO;

import com.practice.p1.Entity.StudentClass;
import com.practice.p1.Entity.StudentMaster;
import com.practice.p1.Entity.SubjectMaster;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClassSubjectMasterDTO {
    Long classSubId;
    StudentClass studentClass;
    SubjectMaster subjects;
    StudentMaster studentInfo;
    int year;

}
