package com.ISA.ISAProject.Dto;

public class ReservationCancelationDTO {
    private Integer ReservationId;
    private Long UpdatedPoints;

    public ReservationCancelationDTO(){

    }
    public ReservationCancelationDTO(Integer reservationId, Long updatedPoints) {
        ReservationId = reservationId;
        UpdatedPoints = updatedPoints;
    }

    public Integer getReservationId() {
        return ReservationId;
    }

    public void setReservationId(Integer reservationId) {
        ReservationId = reservationId;
    }

    public Long getUpdatedPoints() {
        return UpdatedPoints;
    }

    public void setUpdatedPoints(Long updatedPoints) {
        UpdatedPoints = updatedPoints;
    }
}
