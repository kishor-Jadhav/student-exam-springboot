package com.practice.p1.Impl;

import java.util.*;
import java.util.stream.Collectors;

import com.practice.p1.DTO.*;
import com.practice.p1.DTO.Responce.StudentExamMarksPaginationDTO;
import com.practice.p1.DTO.Responce.StudentExamMarksPaginationListDTO;
import com.practice.p1.DTO.Responce.StudentExamSearchResponseDTO;
import com.practice.p1.DTO.Responce.SubjectwiseMarksResponseDTO;
import com.practice.p1.DTO.Search.StudentExamSearchDTO;
import com.practice.p1.Entity.*;
import com.practice.p1.Interface.StudentInfoView;
import com.practice.p1.Repo.StudentClassRepo;
import com.practice.p1.Repo.SubjectMasterRepo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.practice.p1.Repo.StudentMasterRepo;
import com.practice.p1.Services.StudentMasterServices;
import org.springframework.util.comparator.Comparators;

@Service
public class StudenrMasterImpl implements StudentMasterServices   {
    @Autowired
    StudentMasterRepo stRepo;
    @Autowired
    StudentClassRepo stclsRepo;

    @Autowired
    private JPAQueryFactory queryFactory;

    @Autowired
    SubjectMasterRepo subRepo;

    @Override
    public List<StudentMaster> getAllStudent() {
        // TODO Auto-generated method stub
        return stRepo.findAll();
    }
    @Transactional
    @Override
    public StudentMaster saveData(StudentMasterDTO ob) {
        StudentMaster st = new StudentMaster();        
        st.setStudentName(ob.getStudentName());
//        StudentClass cl = stclsRepo.findById(ob.getStdClassId())
//                .orElseThrow(()->new RuntimeException("Student class not found"));
//        st.setStudClass(cl);
        stRepo.save(st);
        return st;
    }

    @Override
    public List<StudentMaster> getStudentByClassNameNo(Long classNo) {
        return stRepo.findByStudClass_StdClassNo(classNo);
    }

    @Override
    public List<StudentMaster> getStudentByClassNoQuury(Long clssNo) {
          return stRepo.findByStudClassNo(clssNo);
    }

    @Override
    public StudentInfoDTO getSubjectOfStudentbyYear(String name, int year) {
        StudentMaster stud = stRepo.findBystudentName(name);
        StudentInfoDTO dto = new StudentInfoDTO();
        dto.setStudentInfo(stud);
        List<ClassSubjectMaster> csmList = stRepo.findSujectByStudent(name,year);
        List<SubjectMaster> subjList = new ArrayList<>();
        for(ClassSubjectMaster ob : csmList) {
            subjList.add(ob.getSubjects());

        }
        dto.setSubjects(subjList);
        return dto;
    }

    @Override
    public List<StudentInfoView> getStudentInfoBySQL() {
        List<StudentInfoView> li= stRepo.getStudentInfoBySQl();
        for(StudentInfoView ob:li){
            System.out.println(ob);
        }
        return li;
    }

    @Override
    public List<StudentInfoJPQL> getStudentInfoJPQL() {

        //Sorting
        List<StudentInfoJPQL>  list =  stRepo.getStudentInfoJPQL();
        list.sort(Comparator.comparing(StudentInfoJPQL::getStudentName));
        return list;
    }

    @Override
    public StudentExamMarksDTO getExamMarks(String studName, String examName) {
        List<StudentExamMarks>  list =  stRepo.getExamMarks(studName,examName);
        List<SubjectMarksDTO> subList = new ArrayList<>();
        StudentExamMarksDTO dto = new StudentExamMarksDTO();
        for(StudentExamMarks item : list){


            dto.setStudentName(item.getStudents().getStudentName());
            dto.setExamName(item.getExamDetails().getExamName());
            SubjectMarksDTO smDTO = new SubjectMarksDTO();
            smDTO.setSubjectName(item.getSubjects().getSubjectName());
            smDTO.setMarks(item.getMarks());
            subList.add(smDTO);

        }
        dto.setSubjectDetails(subList);
        return dto;
    }

    @Override
    public List<StudentExamMarksDTO> getAllStudentExamMarks(String examName) {
        List<StudentExamMarks>  list =  stRepo.getAllStudentExamMarks(examName);

        //Grop by Student Name
        Map<String, List<StudentExamMarks>> grouped =  list.stream()
                        .collect(Collectors.groupingBy(
                                x -> x.getStudents().getStudentName()
                        ));
        System.out.println(grouped);


        List<StudentExamMarksDTO> response = new ArrayList<>();

        for(Map.Entry<String,List<StudentExamMarks>> entry : grouped.entrySet()) {
            String mapKey = entry.getKey();

            //DTO Object
            StudentExamMarksDTO dtoObj= new StudentExamMarksDTO();

            dtoObj.setStudentName(mapKey);  //Student Name
            dtoObj.setExamName(entry.getValue().get(0).getExamDetails().getExamName()); // Exam name

            //Get subject details
            List<SubjectMarksDTO> subList = new ArrayList<>();
            int totalMarks= 0;
            for(StudentExamMarks examOb : entry.getValue()) {
                SubjectMarksDTO sub = new SubjectMarksDTO();

                sub.setSubjectName(examOb.getSubjects().getSubjectName());
                sub.setMarks(examOb.getMarks());
                totalMarks += examOb.getMarks();
                subList.add(sub);
            }
           //////////////
            subList.sort(Comparator.comparing(SubjectMarksDTO:: getSubjectName)); //Sort by subject name
            dtoObj.setSubjectDetails(subList); //SubjectList

            dtoObj.setTotalMarks(totalMarks); //Total Marks

            response.add(dtoObj); //Main Response list
            }
            response.sort(Comparator.comparing(StudentExamMarksDTO::getStudentName)); //sort by Student name
            return response;
    }

    @Override
    public StudentExamMarksPaginationDTO getAllStudentExamMarksPagination(
                                                                          int pageNo,
                                                                          int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<StudentExamMarks> dataList = stRepo.getAllStudentExamMarksPagination(pageable);

        Map<String,List<StudentExamMarks>> grouped =    dataList.getContent().stream()
                .collect(Collectors.groupingBy(
                        x -> x.getStudents().getStudentName()
                ));
        System.out.println(grouped);
        List<StudentExamMarksDTO> response = new ArrayList<>();
        for(Map.Entry<String,List<StudentExamMarks>> entry : grouped.entrySet()) {
            StudentExamMarksDTO dto = new StudentExamMarksDTO();
            dto.setStudentName(entry.getKey());
            dto.setExamName(entry.getValue().get(0).getExamDetails().getExamName());
            List<SubjectMarksDTO> subList = new ArrayList<>();
            int totalMarks= 0;
            for(StudentExamMarks valueObj : entry.getValue()){
                SubjectMarksDTO sub = new SubjectMarksDTO();
                sub.setSubjectName(valueObj.getSubjects().getSubjectName());
                sub.setMarks(valueObj.getMarks());
                totalMarks += valueObj.getMarks();
                subList.add(sub);
            }
            dto.setTotalMarks(totalMarks);
            dto.setSubjectDetails(subList);
            response.add(dto);

        }

        StudentExamMarksPaginationDTO paginationDTO=new StudentExamMarksPaginationDTO();
        paginationDTO.setContent(response);
        paginationDTO.setPageNo(dataList.getNumber());
        paginationDTO.setPageSize(dataList.getSize());
        paginationDTO.setTotalElements(
                dataList.getTotalElements());
        paginationDTO.setTotalPages(dataList.getTotalPages());
        paginationDTO.setLast(dataList.isLast());

 
        return paginationDTO;
    }

    @Override
    public StudentExamMarksPaginationListDTO getAllExamMarksPagination(int pageNo, int pageSize) {
        Pageable page = PageRequest.of(pageNo,pageSize);
        Page<StudentExamMarks> dataList =  stRepo.getAllExamMarksPagination(page);
        List<StudentExamMarksPaginationResponseDTO> dto = new ArrayList<>();
        for(StudentExamMarks ob : dataList.getContent()){
            StudentExamMarksPaginationResponseDTO st = new StudentExamMarksPaginationResponseDTO();
            st.setStudentName(ob.getStudents().getStudentName());
            st.setExamName(ob.getExamDetails().getExamName());
            st.setSubjectName(ob.getSubjects().getSubjectName());
            st.setMarks(ob.getMarks());
            dto.add(st);
        }
        StudentExamMarksPaginationListDTO sendresponse = new StudentExamMarksPaginationListDTO();
        sendresponse.setContent(dto);
        sendresponse.setPageNo(dataList.getNumber());
        sendresponse.setPageSize(dataList.getSize());
        sendresponse.setTotalElements(
                dataList.getTotalElements());
        sendresponse.setTotalPages(dataList.getTotalPages());
        sendresponse.setLast(dataList.isLast());
        return sendresponse;
    }

    @Override
    public List<StudentExamSearchResponseDTO> searchStudent(StudentExamSearchDTO dto) {
        QStudentMaster qStud = QStudentMaster.studentMaster;
        QStudentClass qClass =QStudentClass.studentClass;
        QStudentExamMarks qExam = QStudentExamMarks.studentExamMarks;
        QExamMaster qExamMaster = QExamMaster.examMaster;
        QSubjectMaster qSub = QSubjectMaster.subjectMaster;

        BooleanBuilder builder = new BooleanBuilder();
        if(dto.getStudentName()!= null && !dto.getStudentName().isBlank()){
            builder.and(qStud.studentName.containsIgnoreCase(dto.getStudentName()));
        }
        if(dto.getStudClass()!= null && !dto.getStudClass().isBlank()){
            builder.and(qClass.studClassName.containsIgnoreCase(dto.getStudClass()));
        }

        if(dto.getExamName()!= null && !dto.getExamName().isBlank()){
            builder.and(qExamMaster.examName.containsIgnoreCase(dto.getExamName()));
        }
        if(dto.getSubjectName()!= null && !dto.getSubjectName().isBlank()){
            builder.and(qSub.subjectName.containsIgnoreCase(dto.getSubjectName()));
        }
        if(dto.getMinMarks()!= null ){
            builder.and(qExam.marks.goe(dto.getMinMarks()));
        }
        if(dto.getMaxMarks()!= null ){
            builder.and(qExam.marks.loe(dto.getMaxMarks()));
        }
        if(dto.getSubjectList()!= null && !dto.getSubjectList().isEmpty() ){
            builder.and(qExam.subjects.subjectName.in(dto.getSubjectList()));
            
        }

       List<StudentExamSearchResponseDTO> result =  queryFactory
               .select(
                       Projections.constructor(
                               StudentExamSearchResponseDTO.class,

                               qStud.studentName,
                               qClass.studClassName,
                               qExamMaster.examName,
                               qSub.subjectName,
                               qExam.marks
                       )
               )
               .from(qExam)
               .join(qExam.students, qStud)
               .join(qExam.examDetails, qExamMaster)
               .join(qExam.students.studClass, qClass)
               .join(qExam.subjects, qSub)
               .where(builder)

               .fetch();
       return result;


    }

   @Override
    public List<SubjectwiseMarksResponseDTO> getSubjectWiseMarkx(StudentExamSearchDTO dto) {
       
        QStudentExamMarks qExam = QStudentExamMarks.studentExamMarks;       
        QSubjectMaster qSub = QSubjectMaster.subjectMaster;

        BooleanBuilder builder = new BooleanBuilder();
         
        if(dto.getSubjectName()!= null && !dto.getSubjectName().isBlank()){
            builder.and(qSub.subjectName.containsIgnoreCase(dto.getSubjectName()));
        }
        

       List<SubjectwiseMarksResponseDTO> result =  queryFactory
               .select(
                       Projections.constructor(
                               SubjectwiseMarksResponseDTO.class, 
                               qSub.subjectName,
                               qExam.marks.sum()
                       )
               )
               .from(qExam)
               .join(qExam.subjects, qSub)
               .where(builder)
               .groupBy(qSub.subjectName)

               .fetch();
       return result;


    }

}
