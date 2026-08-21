package com.example.excelHandler.controller;

import com.example.excelHandler.entity.ExcelRecord;
import com.example.excelHandler.repository.ExcelRecordRepository;
import com.example.excelHandler.response.SuccessResponseDto;
import com.example.excelHandler.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public ResponseEntity<SuccessResponseDto<Integer>> uploadExcel(
            @RequestParam("file") MultipartFile file) {
        log.info("File name:{}", file.getOriginalFilename());
        return excelService.readAndSave(file);
    }

    @GetMapping
    public ResponseEntity<List<ExcelRecord>> getAllRecords() {

        List<ExcelRecord> records = excelRecordRepository.findAll();

        return ResponseEntity.ok(records);
    }
}
