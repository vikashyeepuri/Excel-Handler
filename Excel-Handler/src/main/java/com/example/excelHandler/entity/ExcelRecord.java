package com.example.excelHandler.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "excel_records")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcelRecord {

    @Id
    @SequenceGenerator(name = "excelRecordSeq", sequenceName = "excel_record_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "excelRecordSeq")
    private Long id;

    @Column(name = "batch_transaction_id")
    private String batchTransactionId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "debit_ref_no", unique = true)
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

}