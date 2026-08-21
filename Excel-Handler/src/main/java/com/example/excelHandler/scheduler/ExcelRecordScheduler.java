package com.example.excelHandler.scheduler;

import com.example.excelHandler.dto.RequestDto;
import com.example.excelHandler.dto.ResponseDto;
import com.example.excelHandler.entity.ExcelRecord;
import com.example.excelHandler.repository.ExcelRecordRepository;
import com.example.excelHandler.service.ExternalApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ExcelRecordScheduler {
    private final ExcelRecordRepository repository;
    private final ExternalApiService externalApiService;

    public ExcelRecordScheduler(ExcelRecordRepository repository, ExternalApiService externalApiService) {
        this.repository = repository;
        this.externalApiService = externalApiService;
    }

    @Scheduled(fixedDelay = 1000 * 15)
    public void processPendingRecords() {
        List<ExcelRecord> pending = repository.findByStatus("PENDING");

        for (ExcelRecord record : pending) {
            try {
                log.info("Making an external call for pending record with ID: {}", record.getId());

                RequestDto requestDto = RequestDto.builder()
                        .productCode("")
                        .postingBranch("")
                        .exchangeRate("")
                        .dealNo("")
                        .dealDate("")
                        .dealReq("")
                        .xReferenceNo("")
                        .debitAccountNo(null)
                        .debitAccountBranch("")
                        .debitAmount(null)
                        .debitCurrency("")
                        .byOrder1("")
                        .byOrder2("")
                        .byOrder3("")
                        .byOrder4("")
                        .byOrder5("")
                        .chargeAccountNo("")
                        .creditAccountNo("")
                        .creditAccountBranch("")
                        .creditAmount(null)
                        .creditCurrency("")
                        .debitDate("")
                        .creditDate("")
                        .authStatus("")
                        .chargeBearer("")
                        .paymentDetails1("")
                        .paymentDetails2("")
                        .paymentDetails3("")
                        .paymentDetails4("")
                        .ultimateBeneficiary1("")
                        .ultimateBeneficiary2("")
                        .ultimateBeneficiary3("")
                        .ultimateBeneficiary4("")
                        .ultimateBeneficiary5("")
                        .acwthInst1("")
                        .acwthInst2("")
                        .acwthInst3("")
                        .acwthInst4("")
                        .acwthInst5("")
                        .receiver("")
                        .remarks("")
                        .orginalTranRef("")
                        .compositeMis1("")
                        .compositeMis2("")
                        .compositeMis3("")
                        .compositeMis4("")
                        .transactionMis1("")
                        .transactionMis2("")
                        .relatedAccount("")
                        .misDetails(null)
                        .contractMasterCustom(null)
                        .settlementAddlDetails(null)
                        .settlementAddlMain(null)
                        .chargeDetails(null)
                        .udfDetails(null)
                        .build();

                ResponseDto responseDto = externalApiService.callExternalApi(requestDto);
                log.info("External API Response: {}", responseDto);
                if(responseDto.getStatus().equals("Success")){ record.setStatus("PROCESSED"); }
                repository.save(record);

            } catch (Exception e) {
                // Leave as PENDING so the next scheduler run can retry it.
                System.err.println("Failed record " + record.getId() + ": " + e.getMessage());
            }
        }
    }
}
