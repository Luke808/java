package com.accenture.smsf.masterdata.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientServiceLevelDto {
    private String id;

    private String code;

    private BigDecimal ety;

    private String name;

    private String parentId;

    private String processId;

    private String processName;
}