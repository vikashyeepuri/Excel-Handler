package com.example.excelHandler.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "EXCEL_RECORDS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcelRecord {

    @Id
    @SequenceGenerator(
            name = "excelRecordSeq",
            sequenceName = "EXCEL_RECORD_SEQ"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "excelRecordSeq"
    )
    private Long id;

    @Column(name = "BATCH_TRANSACTION_ID")
    private String batchTransactionId;

    @Column(name = "PRODUCT_ID")
    private String productId;

    @Column(name = "DEBIT_REF_NO", unique = true)
    private String debitRefNo;

    @Column(name = "DEBIT_ACCOUNT_NO")
    private String debitAccountNo;

    @Column(name = "TRANSFER_BRANCH")
    private String transferBranch;

    @Column(name = "DEBIT_CURRENCY")
    private String debitCurrency;

    @Column(name = "DEBIT_NARRATION1")
    private String debitNarration1;

    @Column(name = "INTERNAL_ACC_FLAG")
    private String internalAccFlag;

    @Column(name = "ORDER_CUST1")
    private String orderCust1;

    @Column(name = "ORDER_CUST2")
    private String orderCust2;

    @Column(name = "ORDER_CUST3")
    private String orderCust3;

    @Column(name = "ORDER_CUST4")
    private String orderCust4;

    @Column(name = "CREDIT_ACCOUNT_NO")
    private String creditAccountNo;

    @Column(name = "TRANSACTION_CODE")
    private String transactionCode;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "CREDIT_CURRENCY")
    private String creditCurrency;

    @Column(name = "CREDIT_NARRATION1")
    private String creditNarration1;

    @Column(name = "CHARGE_BEARER")
    private String chargeBearer;

    @Column(name = "PAYMENT_DETAILS")
    private String paymentDetails;

    @Column(name = "BEN_NAME")
    private String benName;

    @Column(name = "BEN_ADDR1")
    private String benAddr1;

    @Column(name = "BEN_ADDR2")
    private String benAddr2;

    @Column(name = "BEN_ADDR3")
    private String benAddr3;

    @Column(name = "AW_INST_BIC_CODE")
    private String awInstBicCode;

    @Column(name = "AW_INST_NAME")
    private String awInstName;

    @Column(name = "AW_INST_ADDR1")
    private String awInstAddr1;

    @Column(name = "AW_INST_ADDR2")
    private String awInstAddr2;

    @Column(name = "AW_INST_ADDR3")
    private String awInstAddr3;

    @Column(name = "TRANS_TYPE_CODE")
    private String transTypeCode;

    @Column(name = "SENDER_TO_RECIEVER_INFO")
    private String senderToRecieverInfo;

    @Column(name = "EMPTY")
    private String empty;

    @Column(name = "X_MSG_ID")
    private String xMsgId;

    @Column(name = "TRANSACTION_REF_NO")
    private String transactionRefNo;

    @Column(name = "STATUS")
    private String status;
}