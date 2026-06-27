package com.practice.p1.Batch.Exacel.StudentData;

import com.practice.p1.Entity.StudentMaster;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.Chunk;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StudentCsvStatusUpdateListener implements ItemWriteListener<StudentMaster> {

    private final ProcessedStudentStore store;

    @Override
    public void beforeWrite(Chunk<? extends StudentMaster> items) {
        // no-op
    }

    @Override
    public void afterWrite(Chunk<? extends StudentMaster> items) {
        if (items == null || items.isEmpty()) {
            return;
        }

        for (StudentMaster student : items.getItems()) {
            store.add(student.getStudentNo());
        }
    }

    @Override
    public void onWriteError(Exception exception, Chunk<? extends StudentMaster> items) {
        // no-op for now
    }
}
