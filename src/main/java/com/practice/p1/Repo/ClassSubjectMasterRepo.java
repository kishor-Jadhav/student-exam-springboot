package com.practice.p1.Repo;

import com.practice.p1.Entity.ClassSubjectMaster;
import com.practice.p1.Entity.StudentMaster;
import com.practice.p1.Entity.SubjectMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ListIterator;
@Repository
public interface ClassSubjectMasterRepo extends JpaRepository<ClassSubjectMaster,Long> {

}
