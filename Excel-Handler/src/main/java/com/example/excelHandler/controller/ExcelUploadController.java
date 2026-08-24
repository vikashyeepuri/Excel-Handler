package com.example.excelHandler.controller;

import com.example.excelHandler.entity.ExcelRecord;
import com.example.excelHandler.response.SuccessResponseDto;
import com.example.excelHandler.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/excel")
public class ExcelUploadController {
    private final ExcelService excelService;

    public ExcelUploadController(
            ExcelService excelService) {
        this.excelService = excelService;
    }

    @PostMapping("/upload")
    public ResponseEntity<SuccessResponseDto<Integer>> uploadExcel(
            @RequestParam("file") MultipartFile file) {
        log.info("File name:{}", file.getOriginalFilename());
        return excelService.readAndSave(file);
    }

    @GetMapping
    public ResponseEntity<SuccessResponseDto<List<ExcelRecord>>> getAllRecords() {

        log.info("Fetching All existing records...");

        return excelService.getAllRecords();
    }
}
