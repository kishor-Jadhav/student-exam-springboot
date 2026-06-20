package com.practice.p1.DTO;

import com.practice.p1.Entity.SubjectMaster;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentExamMarksPaginationResponseDTO {
    String studentName;
    String examName;
    String subjectName;
    int marks =0;
}
