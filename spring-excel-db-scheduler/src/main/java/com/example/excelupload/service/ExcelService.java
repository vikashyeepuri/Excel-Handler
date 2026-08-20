package com.example.excelupload.service;

import com.example.excelupload.entity.ExcelRecord;
import com.example.excelupload.repository.ExcelRecordRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {
    private final ExcelRecordRepository repository;

    public ExcelService(ExcelRecordRepository repository) {
        this.repository = repository;
    }

    public int readAndSave(MultipartFile file) {
        validateFile(file);

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            if (sheet.getRow(0) == null) return 0;

            List<ExcelRecord> records = new ArrayList<>();

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;

                ExcelRecord record = new ExcelRecord();

                // Change these mappings for your real Excel/entity.
                record.setName(getCellValue(row.getCell(0)));
                record.setAge(getCellValue(row.getCell(1)));
                record.setCity(getCellValue(row.getCell(2)));
                record.setStatus("PENDING");

                records.add(record);
            }

            repository.saveAll(records);
            return records.size();

        } catch (Exception e) {
            throw new RuntimeException("Unable to read and save Excel file", e);
        }
    }

    private void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Excel file is empty");
        }

        String name = file.getOriginalFilename();
        if (name == null ||
                !(name.toLowerCase().endsWith(".xlsx") ||
                  name.toLowerCase().endsWith(".xls"))) {
            throw new IllegalArgumentException("Only Excel files are allowed");
        }
    }

    private String getCellValue(Cell cell) {
        return cell == null ? "" : new DataFormatter().formatCellValue(cell);
    }
}
