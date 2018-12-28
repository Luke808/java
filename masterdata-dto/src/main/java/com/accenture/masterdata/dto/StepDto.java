package com.accenture.masterdata.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StepDto {
    private String id;
    private String name;
    private String processId;
    private String processName;
}
