package com.accenture.smsf.masterdata.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SptDto {
    private String id;

    private String processId;

    private String stepId;

    private BigDecimal spt;

    private String processName;

    private String stepName;
}
