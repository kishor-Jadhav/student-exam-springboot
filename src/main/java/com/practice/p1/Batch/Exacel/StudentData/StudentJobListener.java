package com.practice.p1.Batch.Exacel.StudentData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentJobListener implements JobExecutionListener {

    private final ProcessedStudentStore store;

    private static final Path CSV_PATH = Paths.get("src/main/resources/input/students.csv");

    @Override
    public void beforeJob(JobExecution jobExecution) {
        store.clear();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        Set<Long> processedStudentNos = store.getStudentNos();
        if (processedStudentNos.isEmpty()) {
            return;
        }

        try {
            updateStatusInCsv(processedStudentNos);
        } catch (IOException e) {
            log.error("Error updating CSV", e);
        } finally {
            store.clear();
        }
    }

    private void updateStatusInCsv(Set<Long> processedStudentNos) throws IOException {
        if (!Files.exists(CSV_PATH)) {
            log.warn("CSV file for status update does not exist: {}", CSV_PATH);
            return;
        }

        List<String> lines = Files.readAllLines(CSV_PATH);
        if (lines.isEmpty()) {
            return;
        }

        String header = lines.get(0);
        String[] columns = header.split(",");
        int studentNoIndex = Arrays.asList(columns).indexOf("studentNo");
        int statusIndex = Arrays.asList(columns).indexOf("status");

        if (studentNoIndex < 0 || statusIndex < 0) {
            log.warn("CSV header does not contain required studentNo or status columns");
            return;
        }

        List<String> updated = new ArrayList<>();
        updated.add(header);

        for (int i = 1; i < lines.size(); i++) {
            String[] values = lines.get(i).split(",", -1);
            if (values.length <= Math.max(studentNoIndex, statusIndex)) {
                updated.add(lines.get(i));
                continue;
            }

            try {
                Long studentNo = Long.parseLong(values[studentNoIndex].trim());
                if (processedStudentNos.contains(studentNo)) {
                    values[statusIndex] = "true";
                }
            } catch (NumberFormatException e) {
                updated.add(lines.get(i));
                continue;
            }

            updated.add(String.join(",", values));
        }

        Files.write(CSV_PATH, updated, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        log.info("CSV Updated Successfully for {} records", processedStudentNos.size());
    }
}
