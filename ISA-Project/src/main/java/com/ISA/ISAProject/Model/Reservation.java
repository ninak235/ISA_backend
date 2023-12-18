package com.ISA.ISAProject.Model;

import com.ISA.ISAProject.Enum.ReservationStatus;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="dateTime")
    private LocalDateTime dateTime;

    @Column(name = "duration")
    private Integer duration;

    @Column(name="grade")
    private Integer grade;

    @Column(name="status")
    private ReservationStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId", nullable = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "companyAdminId", nullable = true)
    private CompanyAdmin companyAdmin;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "reservation_equipment",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private Set<Equipment> reservationEquipments = new HashSet<>();



    public Reservation(Integer id, LocalDateTime dateTime, Integer duration, Integer grade, ReservationStatus status, Customer customer, CompanyAdmin companyAdmin){//companyEquipments) {
        this.id = id;
        this.dateTime = dateTime;
        this.duration = duration;
        this.grade = grade;
        this.status = status;
        this.customer = customer;
        this.companyAdmin = companyAdmin;
        //this.companyEquipments = new HashSet<>();//companyEquipments;
    }

    public Reservation(Reservation reservation){
        this.id = reservation.getId();
        this.dateTime = reservation.getDateTime();
        this.duration = reservation.getDuration();
        this.grade = reservation.getGrade();
        this.status = reservation.getStatus();
        this.customer = reservation.getCustomer();
        this.companyAdmin = reservation.getCompanyAdmin();
        this.reservationEquipments = reservation.getReservationEquipments();
    }



    public Reservation() {
        reservationEquipments = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CompanyAdmin getCompanyAdmin() {
        return companyAdmin;
    }

    public void setCompanyAdmin(CompanyAdmin companyAdmin) {
        this.companyAdmin = companyAdmin;
    }

    public Set<Equipment> getReservationEquipments() {
        return reservationEquipments;
    }

    public void setReservationEquipments(Set<Equipment> equipments) {
        this.reservationEquipments = equipments;
    }
}
