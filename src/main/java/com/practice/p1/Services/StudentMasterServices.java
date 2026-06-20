package com.practice.p1.Services;

import java.util.ArrayList;
import java.util.List;

import com.practice.p1.DTO.*;
import com.practice.p1.DTO.Responce.StudentExamMarksPaginationDTO;
import com.practice.p1.DTO.Responce.StudentExamMarksPaginationListDTO;
import com.practice.p1.DTO.Responce.StudentExamSearchResponseDTO;
import com.practice.p1.DTO.Responce.SubjectwiseMarksResponseDTO;
import com.practice.p1.DTO.Search.StudentExamSearchDTO;
import com.practice.p1.Entity.ClassSubjectMaster;
import com.practice.p1.Entity.StudentExamMarks;
import com.practice.p1.Interface.StudentInfoView;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.practice.p1.Entity.StudentMaster;
 
public interface StudentMasterServices{

    List<StudentMaster> getAllStudent();
    StudentMaster saveData(StudentMasterDTO ob);
    List<StudentMaster> getStudentByClassNameNo(Long clssNo);
    List<StudentMaster> getStudentByClassNoQuury(Long clssNo);
    StudentInfoDTO getSubjectOfStudentbyYear(String name, int year);
    List<StudentInfoView> getStudentInfoBySQL();
    List<StudentInfoJPQL> getStudentInfoJPQL();
    StudentExamMarksDTO getExamMarks(String studName, String examName);
    //Get marks of all students
    List<StudentExamMarksDTO> getAllStudentExamMarks(String examName);
    StudentExamMarksPaginationDTO getAllStudentExamMarksPagination(
                                                                   int pageNo,
                                                                   int pageSize);
    StudentExamMarksPaginationListDTO getAllExamMarksPagination(
            int pageNo,
            int pageSize);

    //Q-Class
    List<StudentExamSearchResponseDTO> searchStudent(StudentExamSearchDTO dto);
    List<SubjectwiseMarksResponseDTO> getSubjectWiseMarkx(StudentExamSearchDTO dto);
}  
