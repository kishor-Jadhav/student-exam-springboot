package com.practice.p1.Batch.Exacel.StudentData;

import com.practice.p1.Entity.StudentClass;
import com.practice.p1.Entity.StudentMaster;
import com.practice.p1.Repo.StudentClassRepo;
import com.practice.p1.Repo.StudentMasterRepo;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StudentProcessor implements ItemProcessor<StudentCsvDTO, StudentMaster> {
    @Autowired
    private StudentClassRepo classRepository;

    @Autowired
    private StudentMasterRepo studRepo;

    @Override
    public StudentMaster process(StudentCsvDTO dto) throws Exception {
       if(!"y".equalsIgnoreCase(dto.getActive())){
        return null;
       }
        if ("true".equalsIgnoreCase(dto.getStatus())  ) {
            return null;
        }
        StudentClass studentClass = classRepository.findByStdClassNo(dto.getClassNo());

        if (studentClass == null) {
             throw new RuntimeException("Class not found: " + dto.getClassNo());
        }
       Boolean isExist = studRepo.existsByStudentNameAndStudEmailAndStudClass_studClassName(dto.getStudentName(),
                dto.getEmail(), studentClass.getStudClassName());
        if (isExist) {
          log.info("Duplicate student found: " + dto.getStudentName()+" Email - "+ dto.getEmail());
          return null;
        }
        StudentMaster student = new StudentMaster();

        student.setStudentNo(dto.getStudentNo());
        student.setStudentName(dto.getStudentName());
        student.setStudMobNo(dto.getMobile());
        student.setStudEmail(dto.getEmail());
        student.setAge(dto.getAge());
        student.setYear(dto.getYear());

        student.setStudClass(studentClass);

        return student;
    }
}
