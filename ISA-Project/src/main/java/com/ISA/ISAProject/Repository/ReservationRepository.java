package com.ISA.ISAProject.Repository;

import com.ISA.ISAProject.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.List;


public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Reservation save(Reservation reservation);

    @Query("SELECT r FROM reservation r JOIN FETCH r.customer WHERE r.companyAdmin.id = :companyAdminId")
    List<Reservation> findByCompanyAdminId(@Param("companyAdminId") Integer companyAdminId);

    List<Reservation> findAllByCustomer_Id(Integer userId);

    List<Reservation> findAllByCompanyAdmin_Id(Integer adminId);

    List<Reservation> findByCompanyAdminIdIn(List<Integer> adminIds);
}
