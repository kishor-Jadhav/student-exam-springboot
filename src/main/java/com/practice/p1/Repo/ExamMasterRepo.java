package com.practice.p1.Repo;

import com.practice.p1.Entity.ExamMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamMasterRepo extends JpaRepository<ExamMaster,Long> {
}
