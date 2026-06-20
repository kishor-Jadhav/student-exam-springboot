package com.practice.p1.DTO;

import com.practice.p1.Entity.StudentMaster;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentClassDTO {
    Long stdClassId;
    String studClassName;
    StudentMaster student;
    Long stdClassNo;
    int year;
}
