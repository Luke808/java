package com.accenture.smsf.masterdata.core.entity;

import com.ac.smsf.codegen.core.annotation.IsId;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_process")
public class Process implements Serializable {
    private static final long serialVersionUID = 4313278267838080145L;

    @Id
    @IsId
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