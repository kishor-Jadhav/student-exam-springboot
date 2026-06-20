package com.practice.p1.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentExamMarksRequestDTO {
    String studentName;
    String examName;
}
