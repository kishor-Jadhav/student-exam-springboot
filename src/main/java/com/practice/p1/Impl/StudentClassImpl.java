package com.practice.p1.Impl;

import com.practice.p1.DTO.StudentClassDTO;
import com.practice.p1.Entity.StudentClass;
import com.practice.p1.Repo.StudentClassRepo;
import com.practice.p1.Services.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentClassImpl implements StudentClassService {
    @Autowired
    StudentClassRepo stdRepo;

    @Override
    public List<StudentClass> getStudentClassList() {
        return stdRepo.findAll();
    }

    @Override
    public StudentClass saveEntity(StudentClassDTO ob) {
        StudentClass st = new StudentClass();
        st.setStudClassName(ob.getStudClassName());
        st.setStdClassNo(ob.getStdClassNo());
        stdRepo.save(st);
        return st;
    }
}
