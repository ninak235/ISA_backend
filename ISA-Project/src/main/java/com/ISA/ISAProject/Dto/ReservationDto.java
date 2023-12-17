package com.ISA.ISAProject.Dto;

import com.ISA.ISAProject.Enum.ReservationStatus;
import com.ISA.ISAProject.Model.CompanyAdmin;
import com.ISA.ISAProject.Model.CompanyEquipment;
import com.ISA.ISAProject.Model.Customer;
import com.ISA.ISAProject.Model.Reservation;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReservationDto {
    private Integer id;

    private LocalDateTime dateTime;

    private Integer duration;

    private Integer grade;

    private ReservationStatus status;

    private Integer customerId;

    private Integer companyAdminId;

    private List<EquipmentDto> reservationEquipments;

    public ReservationDto() {}

    public ReservationDto(Reservation reservation){
        this.id = reservation.getId();
        this.dateTime = reservation.getDateTime();
        this.duration = reservation.getDuration();
        this.grade = reservation.getGrade();
        this.status = reservation.getStatus();
        this.customerId = reservation.getCustomer().getId();
        this.companyAdminId = reservation.getCompanyAdmin().getId();
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCompanyAdminId() {
        return companyAdminId;
    }

    public void setCompanyAdminId(Integer companyAdminId) {
        this.companyAdminId = companyAdminId;
    }

    public List<EquipmentDto> getReservationEquipments() {
        return reservationEquipments;
    }

    public void setReservationEquipments(List<EquipmentDto> reservationEquipments) {
        this.reservationEquipments = reservationEquipments;
    }
}
