package com.example.excelupload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettlementAddlMainDto {

    private String amountTag;

    private String orderingCustomerAddr1;
    private String orderingCustomerAddr2;
    private String orderingCustomerAddr3;
    private String orderingCustomerAddr4;
    private String orderingCustomerAddr5;
    private String orderingCustomerAddr6;

    private String senderInfo1;
    private String senderInfo2;
    private String senderInfo3;
    private String senderInfo4;
    private String senderInfo5;
    private String senderInfo6;

    private String messageThrough;
    private String transTypeCode;
    private String purposeCode;

    private String intermAddr1;
    private String intermAddr2;
    private String intermAddr3;
    private String intermAddr4;
    private String intermAddr5;

    private String orderInst1;
    private String transactionType;
    private String addlTxnDetails;
}