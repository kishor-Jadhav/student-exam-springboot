package com.practice.p1.Batch.Exacel.StudentData;

import com.practice.p1.Entity.StudentMaster;
import jakarta.persistence.EntityManagerFactory;

import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration("studentWriterConfig")
public class StudentWriter {
    @Bean
    public JpaItemWriter<StudentMaster> studentWriter(EntityManagerFactory entityManagerFactory) {

        return new JpaItemWriterBuilder<StudentMaster>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}
