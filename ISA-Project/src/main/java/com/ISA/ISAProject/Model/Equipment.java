package com.ISA.ISAProject.Model;

import com.ISA.ISAProject.Enum.EquipmentStatus;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Objects;

@Where(clause = "deleted = false")
@Entity(name = "Equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "EquipmentName" , nullable = false)
    private String name;

    @Column(name = "Description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status",nullable = false)
    private EquipmentStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CompanyId")
    private Company company;

    /*
    @OneToMany(mappedBy = "equipment")
    private List<Reservation> reservations;
    */

    @Column(name = "deleted")
    private boolean deleted;

    public Equipment(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EquipmentStatus getStatus() {
        return status;
    }

    public void setStatus(EquipmentStatus status) {
        this.status = status;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return Objects.equals(id, equipment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", company=" + company +
                '}';
    }
}
