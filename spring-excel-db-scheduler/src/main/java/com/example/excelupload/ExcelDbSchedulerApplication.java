package com.example.excelupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ExcelDbSchedulerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExcelDbSchedulerApplication.class, args);
    }
}


