package com.ISA.ISAProject.Dto;

import com.ISA.ISAProject.Enum.ReservationStatus;
import com.ISA.ISAProject.Model.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ReservationDto {
    private Integer id;

    private LocalDateTime dateTime;

    private Integer duration;

    private Integer grade;

    private ReservationStatus status;

    private Integer customerId;

    private Integer companyAdminId;

    private String customerName;
    private String customerLastName;
    private List<ReservationEquipmentDto> reservationOfEquipments = new ArrayList<>();

    public List<ReservationEquipmentDto> getReservationOfEquipments() {
        return reservationOfEquipments;
    }

    public void setReservationOfEquipments(List<ReservationEquipmentDto> reservationOfEquipments) {
        this.reservationOfEquipments = reservationOfEquipments;
    }


    public ReservationDto() {
        reservationOfEquipments = new ArrayList<>();
    }


    public ReservationDto(Reservation reservation){
        this.id = reservation.getId();
        this.dateTime = reservation.getDateTime();
        this.duration = reservation.getDuration();
        this.grade = reservation.getGrade();
        this.status = reservation.getStatus();
        this.customerId = reservation.getCustomer().getId();
        this.companyAdminId = reservation.getCompanyAdmin().getId();

        Customer customer = reservation.getCustomer();
        if (customer != null) {
            this.customerName = customer.getUser().getFirstName();
            this.customerLastName = customer.getUser().getLastName();
        }

        Set<ReservationEquipment> equipmentSet = reservation.getReservationOfEquipments();
        List<ReservationEquipmentDto> resEquipmentDtoSet = new ArrayList<>();
        for (ReservationEquipment resEq: equipmentSet) {
            ReservationEquipmentDto reservationEquipmentDto = new ReservationEquipmentDto(resEq.getQuantity(), resEq.getEquipment().getName());
            resEquipmentDtoSet.add(reservationEquipmentDto);
        }
        this.reservationOfEquipments = resEquipmentDtoSet;

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }
    /*public List<EquipmentDto> getReservationEquipments() {
        return reservationEquipments;
    }

    public void setReservationEquipments(List<EquipmentDto> reservationEquipments) {
        this.reservationEquipments = reservationEquipments;

    }*/
}
