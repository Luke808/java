package com.accenture.masterdata.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TatDto {
    private String id;
    private String processId;
    private BigDecimal tat;
    private String processName;
}
