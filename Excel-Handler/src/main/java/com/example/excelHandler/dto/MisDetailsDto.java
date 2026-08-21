package com.example.excelHandler.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MisDetailsDto {

    private String compositeMis1;
    private String compositeMis2;
    private String compositeMis3;
    private String compositeMis4;
    private String transactionMis1;
    private String transactionMis2;
    private String relatedAccount;
}