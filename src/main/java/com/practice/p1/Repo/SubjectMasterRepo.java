package com.practice.p1.Repo;

import com.practice.p1.Entity.SubjectMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectMasterRepo extends JpaRepository<SubjectMaster,Long> {
}
