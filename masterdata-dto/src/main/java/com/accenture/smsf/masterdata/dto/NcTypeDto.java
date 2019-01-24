package com.accenture.smsf.masterdata.dto;

import lombok.Data;

@Data
public class NcTypeDto {
    private String id;

    private String typeName;

    private String parentId;

    private String processId;
    private String processName;
}
