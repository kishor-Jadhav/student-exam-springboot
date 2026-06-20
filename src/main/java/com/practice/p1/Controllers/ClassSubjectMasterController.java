package com.practice.p1.Controllers;

import com.practice.p1.DTO.ClassSubjectMasterDTO;
import com.practice.p1.Entity.ClassSubjectMaster;
import com.practice.p1.Services.ClassSubjectMasterService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseBody
@RestController
@RequestMapping("/api/")
public class ClassSubjectMasterController {
    @Autowired
    ClassSubjectMasterService ob;

    @GetMapping("classsubject")
    List<ClassSubjectMasterDTO> getAllData(){
        return  ob.getAllData();
    }
    @PostMapping("classsubject")
    ClassSubjectMaster saveData(@RequestBody ClassSubjectMasterDTO dto){
        return ob.saveData(dto);
    }

}
