package com.accenture.masterdata.core.entity;

import javax.persistence.*;

@Table(name = "t_step")
public class Step {
    @Id
    private Long id;

    private String name;

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