package com.ISA.ISAProject.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "UserId",nullable = false)
    private User user;

    @Column(name = "Occupation",nullable = false)
    private String occupation;

    @Column(name = "CompanyInfo",nullable = false)
    private String companyInfo;

    @Column(name = "PenaltyPoints")
    private long penaltyPoints;

    public Customer() {
        this.penaltyPoints = 0;
    }

    public Customer(User user, String occupation, String companyInfo) {
        this.user = user;
        this.occupation = occupation;
        this.companyInfo = companyInfo;
        this.id = user.getId();
        this.penaltyPoints= 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    public long getPenaltyPoints() {
        return penaltyPoints;
    }

    public void setPenaltyPoints(long penaltyPoints) {
        this.penaltyPoints = penaltyPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer that = (Customer) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user);
    }


}
