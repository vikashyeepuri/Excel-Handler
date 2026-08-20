package com.example.excelupload.service;

import com.example.excelupload.entity.ExcelRecord;
import com.example.excelupload.repository.ExcelRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ExcelService {
    private final ExcelRecordRepository repository;

    public ExcelService(ExcelRecordRepository repository) {
        this.repository = repository;
    }

    public int readAndSave(MultipartFile file, String traceId) {
        validateFile(file);

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            if (sheet.getRow(0) == null) {
                log.info("The Sheet is Empty");
                return 0;
            }

            List<ExcelRecord> records = new ArrayList<>();

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);

                if (row == null) {
                    log.info("Row number: {} is Empty",  rowIndex);
                }

                ExcelRecord record = ExcelRecord.builder()
                        .id(null)
                        .batchTransactionId(getCellValue(row.getCell(0)))
                        .productId(getCellValue(row.getCell(1)))
                        .debitRefNo(getCellValue(row.getCell(2)))
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

                records.add(record);
            }

            repository.saveAll(records);
            return records.size();

        } catch (Exception e) {
            log.error("Request ID: {}, unable to read and save excel file", traceId);
            throw new RuntimeException("Request ID: " + traceId + " Unable to read and save Excel file", e);
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
        return cell == null ? "" : new DataFormatter().formatCellValue(cell).trim();
    }

    private BigDecimal getBigDecimalValue(Cell cell) {
        if (cell == null) { return null; }
        String value = new DataFormatter().formatCellValue(cell).trim();
        if (value.isEmpty()) { return null; }
        return new BigDecimal(value);
    }
}
