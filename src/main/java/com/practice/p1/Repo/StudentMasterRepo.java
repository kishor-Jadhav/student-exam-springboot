package com.practice.p1.Repo;

import java.util.ArrayList;
import java.util.List;

import com.practice.p1.DTO.StudentInfoJPQL;
import com.practice.p1.Entity.ClassSubjectMaster;
import com.practice.p1.Entity.StudentExamMarks;
import com.practice.p1.Interface.StudentInfoView;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.practice.p1.Entity.StudentMaster;
@Repository
public interface StudentMasterRepo extends JpaRepository<StudentMaster,Long> {
 List<StudentMaster> findByStudClass_StdClassNo(Long id);
 StudentMaster findBystudentName (String name);
 @Query("Select s from StudentMaster s where s.studClass.stdClassNo = :clssNo")
 List<StudentMaster> findByStudClassNo( @Param("clssNo") Long clssNo);

 @Query("Select  cs from ClassSubjectMaster cs  join StudentClass sc on cs.studClass.stdClassId = sc.stdClassId join StudentMaster s  on sc.stdClassId=s.studClass.stdClassId   where  s.studentName =:studName and sc.year =:year ")
 List<ClassSubjectMaster> findSujectByStudent(@Param("studName") String studName, @Param("year") int year );

 //InterFace view SQL Mapping
 @Query(value = "Select studOb.student_id as studentId,studOb.student_name as studentName,studOb.student_no as studentNo,studClassOb.year ,studClassOb.stud_class_name as className from student_master as studOb " +
         "inner join student_class as studClassOb on studOb.std_class_id=studClassOb.std_class_id",nativeQuery = true)
 List<StudentInfoView> getStudentInfoBySQl();

 //JPQL DTO Mapping
 @Query("Select  new com.practice.p1.DTO.StudentInfoJPQL(s.studentId, s.studentName, s.studentNo, sc.studClassName, sc.stdClassNo, sm.subjectName ) from ClassSubjectMaster cs  join StudentClass sc on cs.studClass.stdClassId = sc.stdClassId join StudentMaster s  on sc.stdClassId=s.studClass.stdClassId join SubjectMaster sm on cs.subjects.subjectId=sm.subjectId   ")
 List<StudentInfoJPQL> getStudentInfoJPQL();

 //JPQL Student exam info
 @Query("Select sem from StudentExamMarks sem join ExamMaster em on sem.examDetails.examId=em.examId join StudentMaster sm on sm.studentId=sem.students.studentId where sm.studentName =:studName and em.examName =:examName ")
 List<StudentExamMarks> getExamMarks(@Param("studName") String studName,@Param("examName") String examName);

 //JPQL Student exam info
 @Query("Select sem from StudentExamMarks sem join ExamMaster em on sem.examDetails.examId=em.examId  where   em.examName =:examName ")
 List<StudentExamMarks> getAllStudentExamMarks( @Param("examName") String examName);

 //Pagination
 @Query("Select sem from StudentExamMarks sem join ExamMaster em on sem.examDetails.examId=em.examId")
 Page<StudentExamMarks> getAllStudentExamMarksPagination(Pageable pageable);

 //Pagination2
 @Query("Select sem from StudentExamMarks sem join ExamMaster em on sem.examDetails.examId=em.examId")
 Page<StudentExamMarks> getAllExamMarksPagination(Pageable pageable);


}