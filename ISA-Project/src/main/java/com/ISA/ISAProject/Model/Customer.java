package com.ISA.ISAProject.Model;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Customers")
public class Customer implements Serializable {

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

    @Column(name = "LastPenaltyPointsDateReset")
    private LocalDateTime lastPenaltyPointsDateReset;

    @OneToMany(mappedBy = "companyAdmin",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Complaint> complaintSet = new HashSet<>();
    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Reservation> reservationSet = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LoyalityProgramId", nullable = true)
    private LoyalityProgram loyalityProgram;

    public Customer() {
        this.penaltyPoints = 0;
        this.complaintSet = new HashSet<>();
    }


    public Customer(User user, String occupation, String companyInfo, Set<Complaint> complaintSet, LocalDateTime lastPenaltyPointsDateReset, LoyalityProgram loyalityProgram) {
        this.user = user;
        this.occupation = occupation;
        this.companyInfo = companyInfo;
        this.id = user.getId();
        this.penaltyPoints= 0;
        this.loyalityProgram = loyalityProgram;
        this.complaintSet = complaintSet;
        this.lastPenaltyPointsDateReset = lastPenaltyPointsDateReset;
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

    public Set<Complaint> getComplaintSet() {
        return complaintSet;
    }

    public void setComplaintSet(Set<Complaint> complaintSet) {
        this.complaintSet = complaintSet;
    }

    public LoyalityProgram getLoyalityProgram() {
        return loyalityProgram;
    }

    public void setLoyalityProgram(LoyalityProgram loyalityProgram) {
        this.loyalityProgram = loyalityProgram;
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


    public Set<Reservation> getReservationSet() {
        return reservationSet;
    }

    public void setReservationSet(Set<Reservation> reservationSet) {
        this.reservationSet = reservationSet;
    }

    public LocalDateTime getLastPenaltyPointsDateReset() {
        return lastPenaltyPointsDateReset;
    }

    public void setLastPenaltyPointsDateReset(LocalDateTime lastPenaltyPointsDateReset) {
        this.lastPenaltyPointsDateReset = lastPenaltyPointsDateReset;
    }
}
