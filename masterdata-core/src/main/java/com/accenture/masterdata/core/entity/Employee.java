package com.accenture.masterdata.core.entity;

import javax.persistence.*;

@Table(name = "t_employee")
public class Employee {
    @Id
    private Long id;

    @Column(name = "direct_manager_id")
    private Long directManagerId;

    private String mail;

    private String name;

    private String organization;

    private Boolean status;

    private String avatar;

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
     * @return direct_manager_id
     */
    public Long getDirectManagerId() {
        return directManagerId;
    }

    /**
     * @param directManagerId
     */
    public void setDirectManagerId(Long directManagerId) {
        this.directManagerId = directManagerId;
    }

    /**
     * @return mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail
     */
    public void setMail(String mail) {
        this.mail = mail;
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
     * @return organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * @param organization
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     * @return status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * @return avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}