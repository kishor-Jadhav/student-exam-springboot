package com.practice.p1.Impl;

import com.practice.p1.DTO.StudentClassDTO;
import com.practice.p1.Entity.StudentClass;
import com.practice.p1.Repo.StudentClassRepo;
import com.practice.p1.Services.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class StudentClassImpl implements StudentClassService {
    @Autowired
    StudentClassRepo stdRepo;

    @Override
    public List<StudentClassDTO> getStudentClassList() {
        List<StudentClass> clsObj = stdRepo.findAll();
        List<StudentClassDTO> dtoList = new ArrayList<>();
        for(StudentClass ob: clsObj){
            StudentClassDTO dt = new    StudentClassDTO();
            dt.setStdClassNo(ob.getStdClassNo());
            dt.setStudClassName(ob.getStudClassName());
            dtoList.add(dt);

        }
        return dtoList;
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
