package com.accenture.masterdata.core.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "t_tat")
public class Tat {
    @Id
    private String id;

    @Column(name = "process_id")
    private String processId;

    private BigDecimal tat;

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