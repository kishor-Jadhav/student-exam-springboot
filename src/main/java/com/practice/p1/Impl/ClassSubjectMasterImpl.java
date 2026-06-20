package com.practice.p1.Impl;

import com.practice.p1.DTO.ClassSubjectMasterDTO;
import com.practice.p1.Entity.ClassSubjectMaster;
import com.practice.p1.Repo.ClassSubjectMasterRepo;
import com.practice.p1.Services.ClassSubjectMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ClassSubjectMasterImpl implements ClassSubjectMasterService {
@Autowired
    ClassSubjectMasterRepo repo;
    @Override
    public List<ClassSubjectMasterDTO> getAllData() {
        List<ClassSubjectMaster> cl=  repo.findAll();

        List<ClassSubjectMasterDTO> subDTO= new ArrayList<>();
        for(ClassSubjectMaster ob : cl){
            ClassSubjectMasterDTO dt = new ClassSubjectMasterDTO();
            dt.setSubjects(ob.getSubjects());
            dt.setStudentClass(ob.getStudClass());
            subDTO.add(dt);

        }
        return subDTO;
    }

    @Override
    public ClassSubjectMaster saveData(ClassSubjectMasterDTO ob) {
        ClassSubjectMaster cs = new ClassSubjectMaster();
        ClassSubjectMasterDTO dto = new ClassSubjectMasterDTO();
        cs.setStudClass(dto.getStudentClass());
        cs.setSubjects(dto.getSubjects());
        repo.save(cs);
        return cs;
    }
}
