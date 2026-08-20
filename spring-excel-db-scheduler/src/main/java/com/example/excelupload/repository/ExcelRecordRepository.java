package com.example.excelupload.repository;

import com.example.excelupload.entity.ExcelRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExcelRecordRepository extends JpaRepository<ExcelRecord, Long> {
    List<ExcelRecord> findByStatus(String status);
}
