package com.example.excelupload.scheduler;

import com.example.excelupload.entity.ExcelRecord;
import com.example.excelupload.repository.ExcelRecordRepository;
import com.example.excelupload.service.ExternalApiService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExcelRecordScheduler {
    private final ExcelRecordRepository repository;
    private final ExternalApiService externalApiService;

    public ExcelRecordScheduler(ExcelRecordRepository repository, ExternalApiService externalApiService) {
        this.repository = repository;
        this.externalApiService = externalApiService;
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void processPendingRecords() {
        List<ExcelRecord> pending = repository.findByStatus("PENDING");

        for (ExcelRecord record : pending) {
            try {
                //TODO : external API Info to be added, As of now marking everything as processed based on the db status
                //externalApiService.callExternalApi(record);
                record.setStatus("PROCESSED");
                repository.save(record);
            } catch (Exception e) {
                // Leave as PENDING so the next scheduler run can retry it.
                System.err.println("Failed record " + record.getId() + ": " + e.getMessage());
            }
        }
    }
}
