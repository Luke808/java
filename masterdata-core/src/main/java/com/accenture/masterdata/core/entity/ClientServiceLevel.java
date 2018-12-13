package com.accenture.masterdata.core.entity;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "t_client_service_level")
public class ClientServiceLevel {
    @Id
    private Long id;

    private String code;

    private BigDecimal ety;

    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "process_id")
    private Long processId;

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
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return ety
     */
    public BigDecimal getEty() {
        return ety;
    }

    /**
     * @param ety
     */
    public void setEty(BigDecimal ety) {
        this.ety = ety;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return parent_id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
}