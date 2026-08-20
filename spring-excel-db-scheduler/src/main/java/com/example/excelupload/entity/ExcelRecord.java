package com.example.excelupload.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "excel_records")
public class ExcelRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "batch_transaction_id")
    private String batchTransactionId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "debit_ref_no")
    private String debitRefNo;

    @Column(name = "debit_account_no")
    private String debitAccountNo;

    @Column(name = "transfer_branch")
    private String transferBranch;

    @Column(name = "debit_currency")
    private String debitCurrency;

    @Column(name = "debit_narration1")
    private String debitNarration1;

    @Column(name = "internal_acc_flag")
    private String internalAccFlag;

    @Column(name = "order_cust1")
    private String orderCust1;

    @Column(name = "order_cust2")
    private String orderCust2;

    @Column(name = "order_cust3")
    private String orderCust3;

    @Column(name = "order_cust4")
    private String orderCust4;

    @Column(name = "credit_account_no")
    private String creditAccountNo;

    @Column(name = "transaction_code")
    private String transactionCode;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "credit_currency")
    private String creditCurrency;

    @Column(name = "credit_narration1")
    private String creditNarration1;

    @Column(name = "charge_bearer")
    private String chargeBearer;

    @Column(name = "payment_details")
    private String paymentDetails;

    @Column(name = "ben_name")
    private String benName;

    @Column(name = "ben_addr1")
    private String benAddr1;

    @Column(name = "ben_addr2")
    private String benAddr2;

    @Column(name = "ben_addr3")
    private String benAddr3;

    @Column(name = "aw_inst_bic_code")
    private String awInstBicCode;

    @Column(name = "aw_inst_name")
    private String awInstName;

    @Column(name = "aw_inst_addr1")
    private String awInstAddr1;

    @Column(name = "aw_inst_addr2")
    private String awInstAddr2;

    @Column(name = "aw_inst_addr3")
    private String awInstAddr3;

    @Column(name = "trans_type_code")
    private String transTypeCode;

    @Column(name = "sender_to_reciever_info")
    private String senderToRecieverInfo;

    @Column(name = "empty")
    private String empty;

    @Column(name = "x_msg_id")
    private String xMsgId;

    @Column(name = "transaction_ref_no")
    private String transactionRefNo;

    @Column(name = "status")
    private String status;

    public ExcelRecord() {
    }

    public Long getId() {
        return id;
    }

    public String getBatchTransactionId() {
        return batchTransactionId;
    }

    public void setBatchTransactionId(String batchTransactionId) {
        this.batchTransactionId = batchTransactionId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDebitRefNo() {
        return debitRefNo;
    }

    public void setDebitRefNo(String debitRefNo) {
        this.debitRefNo = debitRefNo;
    }

    public String getDebitAccountNo() {
        return debitAccountNo;
    }

    public void setDebitAccountNo(String debitAccountNo) {
        this.debitAccountNo = debitAccountNo;
    }

    public String getTransferBranch() {
        return transferBranch;
    }

    public void setTransferBranch(String transferBranch) {
        this.transferBranch = transferBranch;
    }

    public String getDebitCurrency() {
        return debitCurrency;
    }

    public void setDebitCurrency(String debitCurrency) {
        this.debitCurrency = debitCurrency;
    }

    public String getDebitNarration1() {
        return debitNarration1;
    }

    public void setDebitNarration1(String debitNarration1) {
        this.debitNarration1 = debitNarration1;
    }

    public String getInternalAccFlag() {
        return internalAccFlag;
    }

    public void setInternalAccFlag(String internalAccFlag) {
        this.internalAccFlag = internalAccFlag;
    }

    public String getOrderCust1() {
        return orderCust1;
    }

    public void setOrderCust1(String orderCust1) {
        this.orderCust1 = orderCust1;
    }

    public String getOrderCust2() {
        return orderCust2;
    }

    public void setOrderCust2(String orderCust2) {
        this.orderCust2 = orderCust2;
    }

    public String getOrderCust3() {
        return orderCust3;
    }

    public void setOrderCust3(String orderCust3) {
        this.orderCust3 = orderCust3;
    }

    public String getOrderCust4() {
        return orderCust4;
    }

    public void setOrderCust4(String orderCust4) {
        this.orderCust4 = orderCust4;
    }

    public String getCreditAccountNo() {
        return creditAccountNo;
    }

    public void setCreditAccountNo(String creditAccountNo) {
        this.creditAccountNo = creditAccountNo;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCreditCurrency() {
        return creditCurrency;
    }

    public void setCreditCurrency(String creditCurrency) {
        this.creditCurrency = creditCurrency;
    }

    public String getCreditNarration1() {
        return creditNarration1;
    }

    public void setCreditNarration1(String creditNarration1) {
        this.creditNarration1 = creditNarration1;
    }

    public String getChargeBearer() {
        return chargeBearer;
    }

    public void setChargeBearer(String chargeBearer) {
        this.chargeBearer = chargeBearer;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public String getBenName() {
        return benName;
    }

    public void setBenName(String benName) {
        this.benName = benName;
    }

    public String getBenAddr1() {
        return benAddr1;
    }

    public void setBenAddr1(String benAddr1) {
        this.benAddr1 = benAddr1;
    }

    public String getBenAddr2() {
        return benAddr2;
    }

    public void setBenAddr2(String benAddr2) {
        this.benAddr2 = benAddr2;
    }

    public String getBenAddr3() {
        return benAddr3;
    }

    public void setBenAddr3(String benAddr3) {
        this.benAddr3 = benAddr3;
    }

    public String getAwInstBicCode() {
        return awInstBicCode;
    }

    public void setAwInstBicCode(String awInstBicCode) {
        this.awInstBicCode = awInstBicCode;
    }

    public String getAwInstName() {
        return awInstName;
    }

    public void setAwInstName(String awInstName) {
        this.awInstName = awInstName;
    }

    public String getAwInstAddr1() {
        return awInstAddr1;
    }

    public void setAwInstAddr1(String awInstAddr1) {
        this.awInstAddr1 = awInstAddr1;
    }

    public String getAwInstAddr2() {
        return awInstAddr2;
    }

    public void setAwInstAddr2(String awInstAddr2) {
        this.awInstAddr2 = awInstAddr2;
    }

    public String getAwInstAddr3() {
        return awInstAddr3;
    }

    public void setAwInstAddr3(String awInstAddr3) {
        this.awInstAddr3 = awInstAddr3;
    }

    public String getTransTypeCode() {
        return transTypeCode;
    }

    public void setTransTypeCode(String transTypeCode) {
        this.transTypeCode = transTypeCode;
    }

    public String getSenderToRecieverInfo() {
        return senderToRecieverInfo;
    }

    public void setSenderToRecieverInfo(String senderToRecieverInfo) {
        this.senderToRecieverInfo = senderToRecieverInfo;
    }

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }

    public String getXMsgId() {
        return xMsgId;
    }

    public void setXMsgId(String xMsgId) {
        this.xMsgId = xMsgId;
    }

    public String getTransactionRefNo() {
        return transactionRefNo;
    }

    public void setTransactionRefNo(String transactionRefNo) {
        this.transactionRefNo = transactionRefNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}