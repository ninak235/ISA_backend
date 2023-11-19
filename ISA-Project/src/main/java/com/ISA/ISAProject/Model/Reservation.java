package com.ISA.ISAProject.Model;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "adminId")
    private CompanyAdmin admin;

    @ManyToOne
    @JoinColumn(name = "equipmentId")
    private Equipment equipment;

    @Column(name = "startTime")
    private LocalDateTime startTime;
    @Column(name = "duration")
    private Duration duration;
    @Column(name = "adminConfirmationDate")
    private LocalDateTime adminConfirmationTime; // When the admin confirmed the reservation
    @Column(name = "confirmed")
    private boolean confirmed; // Indicates if the reservation is confirmed


    public Reservation() {

    }

    public Reservation(CompanyAdmin admin, Equipment equipment, LocalDateTime startTime, Duration duration) {
        this.admin = admin;
        this.equipment = equipment;
        this.startTime = startTime;
        this.duration = duration;
        this.adminConfirmationTime = null;
        this.confirmed = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CompanyAdmin getAdmin() {
        return admin;
    }

    public void setAdmin(CompanyAdmin admin) {
        this.admin = admin;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public LocalDateTime getAdminConfirmationTime() {
        return adminConfirmationTime;
    }

    public void setAdminConfirmationTime(LocalDateTime adminConfirmationTime) {
        this.adminConfirmationTime = adminConfirmationTime;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", admin=" + admin +
                ", equipment='" + equipment + '\'' +
                ", startTime='" + startTime + '\'' +
                ", duration=" + duration + '\'' +
                ", adminConfirmationTime=" + adminConfirmationTime + '\'' +
                ", confirmed=" + confirmed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation reservation = (Reservation) o;
        return Objects.equals(id, reservation.id);
    }
}
