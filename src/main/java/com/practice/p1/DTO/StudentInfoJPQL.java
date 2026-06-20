package com.practice.p1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentInfoJPQL {
    private Long studentId;
    private String studentName;
    private Long studentNo;

    String studClassName;
    Long stdClassNo;

    String subjectName;
}
