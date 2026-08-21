package com.example.excelHandler.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettlementAddlDetailsDto {

    private String amountTag;
    private String generateMessage;
    private String accountNo;
    private String accountBranch;
}