package com.ISA.ISAProject.Model;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class AvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "adminId")
    private CompanyAdmin admin;
    @Column(name = "startTime")
    private LocalDateTime startTime;
    @Column(name = "duration")
    private Integer duration;

    @Column(name = "taken")
    private boolean taken;


    public AvailableDate() {

    }

    public AvailableDate(CompanyAdmin admin, LocalDateTime startTime, Integer duration) {
        this.admin = admin;
        this.startTime = startTime;
        this.duration = duration;
        this.taken = false;
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

    public boolean getTaken(){
        return taken;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AvailableDate{" +
                "id=" + id +
                ", admin=" + admin +
                ", startTime='" + startTime + '\'' +
                ", duration=" + duration + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailableDate availableDate = (AvailableDate) o;
        return Objects.equals(id, availableDate.id);
    }
}
