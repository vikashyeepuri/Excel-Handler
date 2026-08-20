package com.example.excelupload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChargeDetailsDto {

    private String chargeComponent;
    private String eventCode;
    private String chargeCurrency;
    private String chargeAmount;
    private String waiver;
}