package com.ISA.ISAProject.Token;

import com.ISA.ISAProject.Model.Reservation;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "ReservationToken")
public class ReservationConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long tokenId;

    @Column(name = "reservationToken")
    private String reservationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = Reservation.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "reservation_id")
    private Reservation reservation;

    public ReservationConfirmationToken(){
    }

    public ReservationConfirmationToken(Reservation reservation){
        this.reservation = reservation;
        this.createdDate = new Date();
        reservationToken = UUID.randomUUID().toString();
    }

    public Long getTokenId() {
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public String getReservationToken() {
        return reservationToken;
    }

    public void setReservationToken(String reservationToken) {
        this.reservationToken = reservationToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
