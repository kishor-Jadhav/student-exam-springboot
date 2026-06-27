package com.practice.p1.Batch.Exacel.StudentData;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ProcessedStudentStore {

    private final Set<Long> studentNos = new HashSet<>();

    public void add(Long studentNo) {
        if (studentNo != null) {
            studentNos.add(studentNo);
        }
    }

    public Set<Long> getStudentNos() {
        return new HashSet<>(studentNos);
    }

    public void clear() {
        studentNos.clear();
    }
}
