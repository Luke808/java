package com.accenture.smsf.masterdata.core.entity;

import com.ac.smsf.codegen.core.annotation.IsId;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_nc_type")
public class NcType {
    @Id
    @IsId
    private String id;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "parent_id")
    @IsId
    private String parentId;

    @Column(name = "process_id")
    @IsId
    private String processId;

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
     * @return type_name
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * @return parent_id
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
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
}