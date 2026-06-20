package com.practice.p1.DTO;

import com.practice.p1.Entity.ClassSubjectMaster;
import com.practice.p1.Entity.StudentMaster;
import com.practice.p1.Entity.SubjectMaster;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentInfoDTO {
    StudentMaster studentInfo;
    List<SubjectMaster> subjects;

}
