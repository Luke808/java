package com.accenture.masterdata.core.entity;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "t_tat")
public class Tat {
    @Id
    private Long id;

    @Column(name = "process_id")
    private Long processId;

    private BigDecimal tat;

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
     * @return tat
     */
    public BigDecimal getTat() {
        return tat;
    }

    /**
     * @param tat
     */
    public void setTat(BigDecimal tat) {
        this.tat = tat;
    }
}