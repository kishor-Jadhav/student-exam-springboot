package com.practice.p1.Controllers;

import com.practice.p1.DTO.StudentClassDTO;
import com.practice.p1.Entity.StudentClass;
import com.practice.p1.Services.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@ResponseBody
public class StudentClassController {
    @Autowired
    StudentClassService os;

    @GetMapping("studclass")
    List<StudentClassDTO> getAllList(){
        return os.getStudentClassList();
    }

    @PostMapping("studclass")
    StudentClass saveData(@RequestBody StudentClassDTO st){
        return os.saveEntity(st);
    }



}
