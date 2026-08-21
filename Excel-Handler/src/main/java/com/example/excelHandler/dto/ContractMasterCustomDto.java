package com.example.excelHandler.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractMasterCustomDto {

    private String suspenseMsgRefNo;
    private String incomingChannel;
    private String field72;
}