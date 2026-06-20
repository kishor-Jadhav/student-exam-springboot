package com.practice.p1.Repo;

import com.practice.p1.Entity.StudentExamMarks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentExamMarksRepo extends JpaRepository<StudentExamMarks,Long> {
}
