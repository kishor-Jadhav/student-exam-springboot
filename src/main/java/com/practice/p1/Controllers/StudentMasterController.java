package com.practice.p1.Controllers;

import java.util.Collection;
import java.util.List;

import com.practice.p1.DTO.*;
import com.practice.p1.DTO.Responce.StudentExamMarksPaginationDTO;
import com.practice.p1.DTO.Responce.StudentExamMarksPaginationListDTO;
import com.practice.p1.DTO.Responce.StudentExamSearchResponseDTO;
import com.practice.p1.DTO.Responce.SubjectwiseMarksResponseDTO;
import com.practice.p1.DTO.Search.StudentExamSearchDTO;
import com.practice.p1.Entity.StudentExamMarks;
import com.practice.p1.Interface.StudentInfoView;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.practice.p1.Entity.StudentMaster;
import com.practice.p1.Services.StudentMasterServices;


@RestController
@ResponseBody
@RequestMapping("/api/v1/")
public class StudentMasterController {
    @Autowired
    StudentMasterServices st;

    @GetMapping("student")
    public List<StudentMaster> getstudent() {
        return st.getAllStudent();
    }
    @PostMapping("student")
    public StudentMaster saveStudentMaster(@RequestBody StudentMasterDTO entity) {
        //TODO: process POST request
        
        return st.saveData(entity);
    }

    @GetMapping("studentbyclassNo/{clssNo}")
    public List<StudentMaster> getstudentByClassNo(@PathVariable Long clssNo ) {
        return st.getStudentByClassNameNo(clssNo);
    }

    @GetMapping("studentclassNo/{clssNo}")
    public List<StudentMaster> getstudentByClassNoQuery(@PathVariable Long clssNo ) {
        return st.getStudentByClassNoQuury(clssNo);
    }

    @PostMapping("studentinfo")
    public StudentInfoDTO getSubjectOfStudentbyYear(@RequestBody StudentMasterDTO entity) {
        //TODO: process POST request
        StudentInfoDTO dto = st.getSubjectOfStudentbyYear(entity.getStudentName(),entity.getYear());;
        System.out.println(dto);
        return dto;
    }
    @GetMapping("studInfoSql")
    List<StudentInfoView> getStudentInfoBySQL(){
        return st.getStudentInfoBySQL();
    }

    @GetMapping("studInfoJpql")
    List<StudentInfoJPQL> getStudentInfoJPQL(){

        return st.getStudentInfoJPQL();
    }

    @PostMapping("studMarks")
    StudentExamMarksDTO getStudentMarks(@RequestBody StudentExamMarksRequestDTO e){
       return st.getExamMarks(e.getStudentName(),e.getExamName());
    }

    @PostMapping("allstudMarks")
    List<StudentExamMarksDTO> getAllStudentExamMarks(@RequestBody StudentExamMarksRequestDTO e){
        return st.getAllStudentExamMarks(e.getExamName());
    }

    @GetMapping("getmarkspaging")
    StudentExamMarksPaginationDTO getStudentInfoJPQL(
            @RequestParam(defaultValue = "0")
            int pageNo,
            @RequestParam(defaultValue = "5")
            int pageSize
    ){

        return st.getAllStudentExamMarksPagination(pageNo, pageSize);
    }

    @GetMapping("getmarkspaging2")
    StudentExamMarksPaginationListDTO getAllExamMarksPagination(
            @RequestParam(defaultValue = "0")
            int pageNo,
            @RequestParam(defaultValue = "5")
            int pageSize
    ){

        return st.getAllExamMarksPagination(pageNo, pageSize);
    }

    @PostMapping("/searchstudInfo")
    public List<StudentExamSearchResponseDTO> searchStudent(
            @RequestBody StudentExamSearchDTO dto){

        return st.searchStudent(dto);
    }

    @PostMapping("/subjectwisemarks")
    public List<SubjectwiseMarksResponseDTO> getSubjectWiseMarkx(
            @RequestBody StudentExamSearchDTO dto){

        return st.getSubjectWiseMarkx(dto);
    }
    
}
