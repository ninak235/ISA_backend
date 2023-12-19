package com.ISA.ISAProject.Model;

import javax.persistence.*;

@Entity(name = "Complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Content",nullable = false)
    private String content;

    @Column(name = "Replay",nullable = false)
    private String replay;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "CompanyAdminId", nullable = false)
    private CompanyAdmin companyAdmin;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "CustomerId", nullable = false)
    private Customer customer;

    public Complaint() {
        this.companyAdmin = new CompanyAdmin();
        this.customer = new Customer();
    }

    public Complaint(String content, String replay, CompanyAdmin companyAdmin, Customer customer) {
        this.content = content;
        this.replay = replay;
        this.companyAdmin = companyAdmin;
        this.customer = customer;
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

    public CompanyAdmin getAdmin() {
        return companyAdmin;
    }

    public void setCompanyAdmin(CompanyAdmin companyAdmin) {
        this.companyAdmin = companyAdmin;
    }


    public Customer getCustomer(){ return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }

}
