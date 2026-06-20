package com.practice.p1.DTO.Responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentExamSearchResponseDTO {
    private String studentName;

    private String className;

    private String examName;

    private String subjectName;

    private Integer marks;

     
}
