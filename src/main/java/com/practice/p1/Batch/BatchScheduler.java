package com.practice.p1.Batch;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchScheduler {
    private final JobLauncher jobLauncher;
    private final Job studentJob;

    public BatchScheduler(JobLauncher jobLauncher,
                          Job studentJob) {

        this.jobLauncher = jobLauncher;
        this.studentJob = studentJob;
    }

    @Scheduled(fixedDelay = 60000)
    public void runBatch() {

        try {

            jobLauncher.run(
                    studentJob,
                    new JobParametersBuilder()
                            .addLong("time", System.currentTimeMillis())
                            .toJobParameters());

            System.out.println("Student Batch Executed");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
