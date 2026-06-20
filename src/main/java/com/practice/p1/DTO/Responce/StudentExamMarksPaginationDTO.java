package com.practice.p1.DTO.Responce;

import com.practice.p1.DTO.StudentExamMarksDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentExamMarksPaginationDTO {
    List<StudentExamMarksDTO> content = new ArrayList<>();
    private int pageNo;

    private int pageSize;

    private long totalElements;

    private int totalPages;

    private boolean last;
}
