package com.practice.p1.Controllers;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("removal")
@RestController
public class BatchController {

    private final JobLauncher jobLauncher;
    private final Job studentJob1;

    public BatchController(JobLauncher jobLauncher,
                           Job studentJob) {
        this.jobLauncher = jobLauncher;
        this.studentJob1 = studentJob;
    }

    @GetMapping("/batch/student/import")
    public String importStudents() throws Exception {

        JobParameters jobParameters =
                new JobParametersBuilder()
                        .addLong("time", System.currentTimeMillis())
                        .toJobParameters();

        jobLauncher.run(studentJob1, jobParameters);

        return "Student Batch Started";
    }
}