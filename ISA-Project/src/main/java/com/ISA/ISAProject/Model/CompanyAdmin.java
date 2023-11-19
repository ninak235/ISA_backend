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
    @JoinColumn(name = "CompanyId")
    private Company company;

    /*
    @OneToMany(mappedBy = "admin")
    private Set<Reservation> reservationsSet = new HashSet<>();; // Use Set if uniqueness matters, and order doesn't
    */

    public CompanyAdmin() {
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    @Override
    public String toString() {
        return "CompanyAdmin{" +
                "id=" + id +
                ", user=" + user +
                ", company=" + company +
                '}';
    }
}
