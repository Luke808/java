package com.accenture.masterdata.core.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_process")
public class Process {
    @Id
    private String id;

    @Column(name = "company_service_level_id")
    private String companyServiceLevelId;

    private String name;

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
     * @return company_service_level_id
     */
    public String getCompanyServiceLevelId() {
        return companyServiceLevelId;
    }

    /**
     * @param companyServiceLevelId
     */
    public void setCompanyServiceLevelId(String companyServiceLevelId) {
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