package com.accenture.masterdata.core.entity;

import javax.persistence.*;

@Table(name = "t_cutoff_time")
public class CutoffTime {
    @Id
    private Long id;

    @Column(name = "process_id")
    private Long processId;

    private String time;

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
     * @return time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }
}