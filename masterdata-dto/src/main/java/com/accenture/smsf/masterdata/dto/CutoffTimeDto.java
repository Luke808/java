package com.accenture.smsf.masterdata.dto;

import lombok.Data;

@Data
public class CutoffTimeDto {
    private String id;

    private String processId;

    private String time;

    private String processName;
}
