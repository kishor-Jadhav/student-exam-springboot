package com.practice.p1.DTO;

import com.practice.p1.Entity.SubjectMaster;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentExamMarksDTO {
    String studentName;
    String examName;
    List<SubjectMarksDTO> subjectDetails;
    int totalMarks =0;
}
