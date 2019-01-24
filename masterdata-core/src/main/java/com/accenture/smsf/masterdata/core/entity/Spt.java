package com.accenture.smsf.masterdata.core.entity;

import com.ac.smsf.codegen.core.annotation.IsId;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "t_spt")
public class Spt {
    @Id
    @IsId
    private String id;

    @Column(name = "process_id")
    @IsId
    private String processId;

    @Column(name = "step_id")
    @IsId
    private String stepId;

    private BigDecimal spt;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return process_id
     */
    public String getProcessId() {
        return processId;
    }

    /**
     * @param processId
     */
    public void setProcessId(String processId) {
        this.processId = processId;
    }

    /**
     * @return step_id
     */
    public String getStepId() {
        return stepId;
    }

    /**
     * @param stepId
     */
    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    /**
     * @return spt
     */
    public BigDecimal getSpt() {
        return spt;
    }

    /**
     * @param spt
     */
    public void setSpt(BigDecimal spt) {
        this.spt = spt;
    }
}