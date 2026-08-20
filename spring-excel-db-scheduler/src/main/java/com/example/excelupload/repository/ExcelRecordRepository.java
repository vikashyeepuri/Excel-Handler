package com.example.excelupload.repository;

import com.example.excelupload.entity.ExcelRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExcelRecordRepository extends JpaRepository<ExcelRecord, Long> {
    List<ExcelRecord> findByStatus(String status);
}
