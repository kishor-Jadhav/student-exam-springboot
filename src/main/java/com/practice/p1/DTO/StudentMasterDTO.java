package com.practice.p1.DTO;

import com.practice.p1.Entity.ClassSubjectMaster;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentMasterDTO {
     private Long studentId;
     private String studentName;
     private Long studentNo;
     private  Long stdClassId;
    private   int year;
}
