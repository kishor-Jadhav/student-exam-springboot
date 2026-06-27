package com.practice.p1.Batch.Exacel.StudentData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class StudentItemReader {

    private static final Logger log = LoggerFactory.getLogger(StudentItemReader.class);

    @Bean
    public FlatFileItemReader<StudentCsvDTO> reader() {
        ClassPathResource resource = new ClassPathResource("input/students.csv");

        if (!resource.exists()) {
            log.warn("Student input file not found at classpath: input/students.csv. Batch reader will be configured in non-strict mode.");
        }

        return new FlatFileItemReaderBuilder<StudentCsvDTO>()
                .name("studentReader")
                .resource(resource)
                .strict(false)
                .delimited()
                .names("studentNo","studentName","mobile","email","age","classNo","year","status","active")
                .linesToSkip(1)
                .targetType(StudentCsvDTO.class)
                .build();
    }

    // .fieldSetMapper(fieldSet -> {
    //             StudentCsvDTO dto = new StudentCsvDTO();
    //             dto.setStudentNo(Long.parseLong(fieldSet.readString("studentNo")));
    //             dto.setStudentName(fieldSet.readString("studentName"));
    //             dto.setMobile(fieldSet.readString("mobile"));
    //             dto.setEmail(fieldSet.readString("email"));
    //             dto.setAge(fieldSet.readInt("age"));
    //             dto.setClassNo(Long.parseLong(fieldSet.readString("classNo")));
    //             dto.setYear(fieldSet.readInt("year"));
    //             dto.setStatus(fieldSet.readString("status"));

    //             // Put your debug breakpoint right on this log statement!
    //             log.info("Successfully read student: {} - {}", dto.getStudentName(), dto.getEmail());
                
    //             return dto;
    //         })
}
