package com.practice.p1.Repo;

import com.practice.p1.Entity.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentClassRepo extends JpaRepository<StudentClass,Long> {
}
