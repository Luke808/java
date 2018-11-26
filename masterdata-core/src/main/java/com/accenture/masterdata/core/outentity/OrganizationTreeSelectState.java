package com.accenture.masterdata.core.outentity;

import lombok.Data;

@Data
public class OrganizationTreeSelectState {

    private boolean opened;

    private boolean disabled;

    private boolean selected;
}
