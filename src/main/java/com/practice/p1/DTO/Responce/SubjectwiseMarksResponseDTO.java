package com.practice.p1.DTO.Responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubjectwiseMarksResponseDTO {
     private String subjectName;   

     private Integer totalMarks;
}
