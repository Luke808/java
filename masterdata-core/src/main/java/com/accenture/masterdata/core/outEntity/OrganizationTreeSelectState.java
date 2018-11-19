package com.accenture.masterdata.core.outEntity;

import lombok.Data;

@Data
public class OrganizationTreeSelectState {

    private boolean opened;

    private boolean disabled;

    private boolean selected;
}
