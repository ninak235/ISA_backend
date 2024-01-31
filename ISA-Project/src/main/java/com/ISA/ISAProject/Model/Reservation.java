package com.ISA.ISAProject.Model;

import com.ISA.ISAProject.Enum.ReservationStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "reservation")
public class Reservation implements Serializable {
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

    @Column(name="price")
    private Float price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId", nullable = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "companyAdminId", nullable = true)
    private CompanyAdmin companyAdmin;

    /*
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "reservation_equipment",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private Set<Equipment> reservationEquipments = new HashSet<>();*/

    @OneToMany(mappedBy = "reservation", fetch = FetchType.EAGER)
    Set<ReservationEquipment> reservationOfEquipments;

    @Version
    private Integer version;

    public Reservation(Integer id, LocalDateTime dateTime, Integer duration, Integer grade, Float price, Customer customer, CompanyAdmin companyAdmin){//companyEquipments) {
        this.id = id;
        this.dateTime = dateTime;
        this.duration = duration;
        this.grade = grade;
        this.price = price;
        this.status = ReservationStatus.Pending;
        this.customer = customer;
        this.companyAdmin = companyAdmin;
        this.reservationOfEquipments = new HashSet<>();
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
        this.reservationOfEquipments = reservation.reservationOfEquipments;
        //this.reservationEquipments = reservation.getReservationEquipments();
        this.price = reservation.getPrice();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Set<ReservationEquipment> getReservationOfEquipments() {
        return reservationOfEquipments;
    }

    public void setReservationOfEquipments(Set<ReservationEquipment> reservationOfEquipments) {
        this.reservationOfEquipments = reservationOfEquipments;
    }


    public Reservation() {
        //reservationEquipments = new HashSet<>();
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
/*
    public Set<Equipment> getReservationEquipments() {
        return reservationEquipments;
    }

    public void setReservationEquipments(Set<Equipment> equipments) {
        this.reservationEquipments = equipments;

    }*/

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
