package com.ISA.ISAProject.Dto;

import com.ISA.ISAProject.Model.AvailableDate;
import com.ISA.ISAProject.Model.Complaint;

import java.time.LocalDateTime;
import java.util.List;

public class AvailableDateDto {
    private Integer id;
    private Integer adminId;
    private LocalDateTime startTime;
    private Integer duration;
    private boolean taken;

    public AvailableDateDto() {
        // Default constructor for Jackson deserialization
    }

    public AvailableDateDto(AvailableDate reservation) {
        this.id = reservation.getId();
        this.adminId = reservation.getAdmin().getId();
        this.startTime = reservation.getStartTime();
        this.duration = reservation.getDuration();
        this.taken = reservation.getTaken();
    }

    // Getters and Setters...

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public boolean getTaken(){return taken; }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}

