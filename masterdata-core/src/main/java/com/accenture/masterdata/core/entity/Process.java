package com.accenture.masterdata.core.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_process")
public class Process {
    @Id
    private Long id;

    @Column(name = "company_service_level_id")
    private Long companyServiceLevelId;

    private String name;

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
     * @return company_service_level_id
     */
    public Long getCompanyServiceLevelId() {
        return companyServiceLevelId;
    }

    /**
     * @param companyServiceLevelId
     */
    public void setCompanyServiceLevelId(Long companyServiceLevelId) {
        this.companyServiceLevelId = companyServiceLevelId;
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
}