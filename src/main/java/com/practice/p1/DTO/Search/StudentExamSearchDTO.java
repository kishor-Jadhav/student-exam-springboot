package com.practice.p1.DTO.Search;

import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentExamSearchDTO {
    private String studentName;

    private String examName;

    private String studClass;

    private String subjectName;

    private Integer minMarks;

    private Integer maxMarks;

    private List<String> subjectList;
}
