package com.example.excelHandler.service;

import com.example.excelHandler.entity.ExcelRecord;
import com.example.excelHandler.repository.ExcelRecordRepository;
import com.example.excelHandler.response.SuccessResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ExcelService {
    private final ExcelRecordRepository repository;

    public ExcelService(ExcelRecordRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<SuccessResponseDto<Integer>> readAndSave(MultipartFile file) {
        validateFile(file);

        try (InputStream inputStream = file.getInputStream();
                Workbook workbook = WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            if (sheet.getRow(0) == null) {
                log.info("The Sheet is Empty");
                throw new IllegalArgumentException("The provided excel sheet is empty");
            }

            List<ExcelRecord> parsedRecords = new ArrayList<>();
            Set<String> debitRefNos = new HashSet<>(); // Used to batch-fetch existing records

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);

                if (row == null) {
                    log.info("Row number: {} is Empty", rowIndex);
                    continue; // Added continue to prevent NullPointerException below
                }

                String debitRefNo = getCellValue(row.getCell(2));
                if (!debitRefNo.trim().isEmpty()) {
                    debitRefNos.add(debitRefNo);
                }

                ExcelRecord record = ExcelRecord.builder()
                        // Leave ID null for now; we will set it below if the record exists
                        .batchTransactionId(getCellValue(row.getCell(0)))
                        .productId(getCellValue(row.getCell(1)))
                        .debitRefNo(debitRefNo)
                        .debitAccountNo(getCellValue(row.getCell(3)))
                        .transferBranch(getCellValue(row.getCell(4)))
                        .debitCurrency(getCellValue(row.getCell(5)))
                        .debitNarration1(getCellValue(row.getCell(6)))
                        .internalAccFlag(getCellValue(row.getCell(7)))
                        .orderCust1(getCellValue(row.getCell(8)))
                        .orderCust2(getCellValue(row.getCell(9)))
                        .orderCust3(getCellValue(row.getCell(10)))
                        .orderCust4(getCellValue(row.getCell(11)))
                        .creditAccountNo(getCellValue(row.getCell(12)))
                        .transactionCode(getCellValue(row.getCell(13)))
                        .amount(getBigDecimalValue(row.getCell(14)))
                        .creditCurrency(getCellValue(row.getCell(15)))
                        .creditNarration1(getCellValue(row.getCell(16)))
                        .chargeBearer(getCellValue(row.getCell(17)))
                        .paymentDetails(getCellValue(row.getCell(18)))
                        .benName(getCellValue(row.getCell(19)))
                        .benAddr1(getCellValue(row.getCell(20)))
                        .benAddr2(getCellValue(row.getCell(21)))
                        .benAddr3(getCellValue(row.getCell(22)))
                        .awInstBicCode(getCellValue(row.getCell(23)))
                        .awInstName(getCellValue(row.getCell(24)))
                        .awInstAddr1(getCellValue(row.getCell(25)))
                        .awInstAddr2(getCellValue(row.getCell(26)))
                        .awInstAddr3(getCellValue(row.getCell(27)))
                        .transTypeCode(getCellValue(row.getCell(28)))
                        .senderToRecieverInfo(getCellValue(row.getCell(29)))
                        .empty(getCellValue(row.getCell(30)))
                        .xMsgId(getCellValue(row.getCell(31)))
                        .transactionRefNo(getCellValue(row.getCell(32)))
                        .status("PENDING")
                        .build();

                parsedRecords.add(record);
            }

            // --- UPSERT LOGIC ---
            if (!debitRefNos.isEmpty()) {

                // Fetch all the records that already exist in our database with those
                // debitRefNos
                List<ExcelRecord> existingRecords = repository.findByDebitRefNoIn(debitRefNos);

                // Create a Map
                // If the debitRefNo exists in DB {"someDebitRefNo" : "somRecordId"}
                // If not {"someDebitRefNo" : null}
                Map<String, Long> existingIdMap = existingRecords.stream()
                        .collect(Collectors.toMap(ExcelRecord::getDebitRefNo, ExcelRecord::getId));

                // If any record already exists in the database then update the ID
                for (ExcelRecord record : parsedRecords) {
                    Long existingId = existingIdMap.get(record.getDebitRefNo());
                    if (existingId != null) {
                        record.setId(existingId);
                    }
                }
            }

            // Spring Data JPA checks the ID: if present, it updates; if null, it inserts.
            repository.saveAll(parsedRecords);

            return new ResponseEntity<>(
                    new SuccessResponseDto<>(
                            "SUCCESS",
                            "Excel sheet has been processed successfully",
                            parsedRecords.size()),
                    HttpStatus.CREATED);

        } catch (Exception e) {
            log.error("Unable to read and save excel file, message: {}", e.getMessage());
            throw new RuntimeException("Unable to read and save Excel file, reason: " + e.getMessage());
        }
    }

    public ResponseEntity<SuccessResponseDto<List<ExcelRecord>>> getAllRecords() {

        List<ExcelRecord> records = repository.findAll();

        return new ResponseEntity<>(
                new SuccessResponseDto<>(
                        "SUCCESS",
                        "Retrived All records Successfully",
                        records),
                HttpStatus.OK);

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
        return cell == null ? "" : new DataFormatter().formatCellValue(cell).trim();
    }

    private BigDecimal getBigDecimalValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        String value = new DataFormatter().formatCellValue(cell).trim();
        if (value.isEmpty()) {
            return null;
        }
        return new BigDecimal(value);
    }
}
