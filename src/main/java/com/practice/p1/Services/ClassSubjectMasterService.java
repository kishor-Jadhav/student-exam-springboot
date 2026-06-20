package com.practice.p1.Services;

import com.practice.p1.DTO.ClassSubjectMasterDTO;
import com.practice.p1.Entity.ClassSubjectMaster;

import java.util.List;

public interface ClassSubjectMasterService {
    List<ClassSubjectMasterDTO> getAllData();
    ClassSubjectMaster saveData(ClassSubjectMasterDTO ob);

}
