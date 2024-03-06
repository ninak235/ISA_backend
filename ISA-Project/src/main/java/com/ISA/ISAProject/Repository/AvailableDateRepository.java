package com.ISA.ISAProject.Repository;

import com.ISA.ISAProject.Model.AvailableDate;
import com.ISA.ISAProject.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AvailableDateRepository extends JpaRepository<AvailableDate,Integer> {


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    AvailableDate save(AvailableDate availableDate);

    List<AvailableDate> findAvailableDatesByAdmin_Company_Id(Integer companyId);

    List<AvailableDate> findAvailableDateByAdmin_Id(Integer adminId);

    Optional<AvailableDate> findAvailableDateByAdmin_IdAndStartTime(Integer adminId, LocalDateTime startTime);

}
