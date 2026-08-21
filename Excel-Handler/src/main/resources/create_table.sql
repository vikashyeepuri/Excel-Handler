CREATE SEQUENCE excel_record_seq
    START WITH 1
    INCREMENT BY 1 NOCACHE
    NOCYCLE;

CREATE TABLE excel_records
(
    id                      NUMBER(19,0) NOT NULL,

    batch_transaction_id    VARCHAR2(255),
    product_id              VARCHAR2(255),
    debit_ref_no            VARCHAR2(255),
    debit_account_no        VARCHAR2(255),
    transfer_branch         VARCHAR2(255),
    debit_currency          VARCHAR2(255),
    debit_narration1        VARCHAR2(255),
    internal_acc_flag       VARCHAR2(255),

    order_cust1             VARCHAR2(255),
    order_cust2             VARCHAR2(255),
    order_cust3             VARCHAR2(255),
    order_cust4             VARCHAR2(255),

    credit_account_no       VARCHAR2(255),
    transaction_code        VARCHAR2(255),
    amount                  NUMBER(19,4),
    credit_currency         VARCHAR2(255),
    credit_narration1       VARCHAR2(255),

    charge_bearer           VARCHAR2(255),
    payment_details         VARCHAR2(255),

    ben_name                VARCHAR2(255),
    ben_addr1               VARCHAR2(255),
    ben_addr2               VARCHAR2(255),
    ben_addr3               VARCHAR2(255),

    aw_inst_bic_code        VARCHAR2(255),
    aw_inst_name            VARCHAR2(255),
    aw_inst_addr1           VARCHAR2(255),
    aw_inst_addr2           VARCHAR2(255),
    aw_inst_addr3           VARCHAR2(255),

    trans_type_code         VARCHAR2(255),
    sender_to_reciever_info VARCHAR2(255),
    empty                   VARCHAR2(255),
    x_msg_id                VARCHAR2(255),
    transaction_ref_no      VARCHAR2(255),
    status                  VARCHAR2(255),

    CONSTRAINT pk_excel_records PRIMARY KEY (id)
);