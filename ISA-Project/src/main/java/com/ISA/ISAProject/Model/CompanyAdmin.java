package com.ISA.ISAProject.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "CompanyAdmins")
public class CompanyAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "UserId",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CompanyId", nullable = true)
    private Company company;

    @Column(name = "UserName", unique = true)
    private String userName;

    @OneToMany(mappedBy = "companyAdmin",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Reservation> reservationSet = new HashSet<>();

    @OneToMany(mappedBy = "companyAdmin",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Complaint> complaintSet = new HashSet<>();

    /*
    @OneToMany(mappedBy = "admin")
    private Set<Reservation> reservationsSet = new HashSet<>();; // Use Set if uniqueness matters, and order doesn't
    */

    public CompanyAdmin() {
        //this.company = new Company();
        this.complaintSet = new HashSet<>();
    }

    /*
    public CompanyAdmin(User user) {
        this.user = user;
        this.id = user.getId();
    }*/

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

    public Company getCompany() {
        return company;
    }
    public Integer getCompanyId() { return company.getId(); }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Complaint> getComplaintSet() {
        return complaintSet;
    }

    public void setComplaintSet(Set<Complaint> complaintSet) {
        this.complaintSet = complaintSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyAdmin that = (CompanyAdmin) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user);
    }

}
