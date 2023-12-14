package com.ISA.ISAProject.Dto;

import com.ISA.ISAProject.Model.AvailableDate;

import java.time.Duration;
import java.time.LocalDateTime;

public class AvailableDateDto {
    private Integer id;
    private CompanyAdminDto admin; // Assuming you have a CompanyDto for administrators
    private LocalDateTime startTime;
    private Duration duration;
    private LocalDateTime adminConfirmationTime;
    private boolean confirmed;

    public AvailableDateDto() {
        // Default constructor for Jackson deserialization
    }

    public AvailableDateDto(AvailableDate reservation) {
        this.id = reservation.getId();
        this.admin = new CompanyAdminDto(reservation.getAdmin().getUser(), reservation.getAdmin().getCompany().getId());
        this.startTime = reservation.getStartTime();
        this.duration = reservation.getDuration();
        this.adminConfirmationTime = reservation.getAdminConfirmationTime();
        this.confirmed = reservation.isConfirmed();
    }

    // Getters and Setters...

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CompanyAdminDto getAdmin() {
        return admin;
    }

    public void setAdmin(CompanyAdminDto admin) {
        this.admin = admin;
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
}

