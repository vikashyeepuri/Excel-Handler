package com.example.excelupload.controller;

import com.example.excelupload.entity.ExcelRecord;
import com.example.excelupload.repository.ExcelRecordRepository;
import com.example.excelupload.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/excel")
public class ExcelUploadController {
    private final ExcelService excelService;
    private final ExcelRecordRepository excelRecordRepository;


    public ExcelUploadController(
            ExcelService excelService,
            ExcelRecordRepository excelRecordRepository) {
        this.excelService = excelService;
        this.excelRecordRepository = excelRecordRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadExcel(
            @RequestParam("file") MultipartFile file,
            @RequestParam("traceId") String traceId) {

        log.info("Request ID:{}, File name:{}", traceId, file.getOriginalFilename());

        int count = excelService.readAndSave(file, traceId);

        return ResponseEntity.ok(Map.of(
                "message", "Excel processed successfully",
                "recordsSaved", count
        ));
    }

    @GetMapping
    public ResponseEntity<List<ExcelRecord>> getAllRecords() {

        List<ExcelRecord> records = excelRecordRepository.findAll();

        return ResponseEntity.ok(records);
    }
}
