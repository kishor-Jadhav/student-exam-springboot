package com.practice.p1.Batch.Exacel.StudentData;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentCsvDTO {
    private Long studentNo;
    private String studentName;
    private String mobile;
    private String email;
    private Integer age;
    private Long classNo;
    private Integer year;
    private String status;
    private String active;
}
