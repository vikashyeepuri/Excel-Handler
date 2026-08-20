package com.example.excelupload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {

    private String productCode;
    private String postingBranch;
    private String exchangeRate;
    private String dealNo;
    private String dealDate;
    private String dealReq;
    private String xReferenceNo;

    private String debitAccountNo;
    private String debitAccountBranch;
    private BigDecimal debitAmount;
    private String debitCurrency;

    private String byOrder1;
    private String byOrder2;
    private String byOrder3;
    private String byOrder4;
    private String byOrder5;

    private String chargeAccountNo;
    private String creditAccountNo;
    private String creditAccountBranch;
    private BigDecimal creditAmount;
    private String creditCurrency;

    private String debitDate;
    private String creditDate;
    private String authStatus;
    private String chargeBearer;

    private String paymentDetails1;
    private String paymentDetails2;
    private String paymentDetails3;
    private String paymentDetails4;

    private String ultimateBeneficiary1;
    private String ultimateBeneficiary2;
    private String ultimateBeneficiary3;
    private String ultimateBeneficiary4;
    private String ultimateBeneficiary5;

    private String acwthInst1;
    private String acwthInst2;
    private String acwthInst3;
    private String acwthInst4;
    private String acwthInst5;

    private String receiver;
    private String remarks;
    private String orginalTranRef;

    private String compositeMis1;
    private String compositeMis2;
    private String compositeMis3;
    private String compositeMis4;
    private String transactionMis1;
    private String transactionMis2;
    private String relatedAccount;

    private MisDetailsDto misDetails;

    private ContractMasterCustomDto contractMasterCustom;

    private SettlementAddlDetailsDto settlementAddlDetails;

    private SettlementAddlMainDto settlementAddlMain;

    private List<ChargeDetailsDto> chargeDetails;

    private List<UdfDetailsDto> udfDetails;
}