package com.practice.p1.Services;

import com.practice.p1.DTO.StudentClassDTO;
import com.practice.p1.Entity.StudentClass;

import java.util.List;

public interface StudentClassService {
    List<StudentClass> getStudentClassList();
    StudentClass saveEntity(StudentClassDTO ob);
}
