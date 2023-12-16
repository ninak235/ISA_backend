package com.ISA.ISAProject.Dto;

import com.ISA.ISAProject.Model.Complaint;

import javax.validation.constraints.NotEmpty;

public class ComplaintDto {

    private Integer id;

    @NotEmpty
    private String content;

    private String replay;

    private Integer companyAdminId;

    private Integer customerId;
    
    public ComplaintDto() {
        
    }

    public ComplaintDto(String content, String replay, Integer companyAdminId, Integer customerId){
        this.content = content;
        this.replay = replay;
        this.companyAdminId = companyAdminId;
        this.customerId = customerId;
    }

    public ComplaintDto(Complaint complaint) {
        this.content = complaint.getContent();
        this.replay = complaint.getReplay();
        this.companyAdminId = complaint.getAdmin().getId();
        this.customerId = complaint.getCustomer().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplay() {
        return replay;
    }

    public void setReplay(String replay) {
        this.replay = replay;
    }

    public Integer getCompanyAdminId() {
        return companyAdminId;
    }

    public void setCompanyAdminId(Integer companyAdminId) {
        this.companyAdminId = companyAdminId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
