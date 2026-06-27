package com.practice.p1.Batch;

import com.practice.p1.Batch.Exacel.StudentData.StudentCsvDTO;
import com.practice.p1.Batch.Exacel.StudentData.StudentCsvStatusUpdateListener;
import com.practice.p1.Batch.Exacel.StudentData.StudentJobListener;
import com.practice.p1.Batch.Exacel.StudentData.StudentProcessor;
import com.practice.p1.Entity.StudentMaster;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;

import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;

import org.springframework.batch.core.step.builder.StepBuilder;

import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchStepConfig {
    @Bean
    public Step studentStep(JobRepository jobRepository,
                            PlatformTransactionManager transactionManager,
                            FlatFileItemReader<StudentCsvDTO> reader,
                            StudentProcessor processor,
                            JpaItemWriter<StudentMaster> writer,
                            StudentCsvStatusUpdateListener statusUpdateListener) {

        return new StepBuilder("studentStep", jobRepository)
                .<StudentCsvDTO, StudentMaster>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(statusUpdateListener)
                .build();
    }
    @Bean
    public Job studentJob(JobRepository jobRepository,
                          Step studentStep,
                          StudentJobListener jobListener) {

        return new JobBuilder("studentJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(jobListener)
                .start(studentStep)
                .build();
    }
}
