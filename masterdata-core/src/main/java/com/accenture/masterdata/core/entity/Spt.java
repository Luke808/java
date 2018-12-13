package com.accenture.masterdata.core.entity;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "t_spt")
public class Spt {
    @Id
    private Long id;

    @Column(name = "process_id")
    private Long processId;

    @Column(name = "step_id")
    private Long stepId;

    private BigDecimal spt;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return process_id
     */
    public Long getProcessId() {
        return processId;
    }

    /**
     * @param processId
     */
    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    /**
     * @return step_id
     */
    public Long getStepId() {
        return stepId;
    }

    /**
     * @param stepId
     */
    public void setStepId(Long stepId) {
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