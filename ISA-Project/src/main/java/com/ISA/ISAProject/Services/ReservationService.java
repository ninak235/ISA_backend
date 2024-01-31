package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.*;
import com.ISA.ISAProject.Enum.ReservationStatus;
import com.ISA.ISAProject.Mapper.EquipmentMapper;
import com.ISA.ISAProject.Mapper.ReservationMapper;
import com.ISA.ISAProject.Mapper.UserMapper;
import com.ISA.ISAProject.Model.*;
import com.ISA.ISAProject.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import javax.transaction.Transactional;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.OptimisticLockException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AvailableDateRepository availableDateRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CompanyAdminService companyAdminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyEquipmentRepository companyEquipmentRepository;
    @Autowired
    private EquipmentRepository _equipmentRepository;
    @Autowired
    private ReservationEquipmentRepository _resEqRepository;


    //@Transactional
    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return  reservationMapper.mapReservationsToDto(reservations);
    }


    public List<ReservationDto> getAllReservationsByCompanyAdminId(Integer companyAdminId) {
        List<Reservation> reservations = reservationRepository.findByCompanyAdminId(companyAdminId);
        return reservationMapper.mapReservationsToDto(reservations);
    }


    public ReservationDto getReservationById(Integer reservationId) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        return optionalReservation.map(reservationMapper::mapReservationToDto).orElse(null);
    }


    public List<ReservationDto> getFutureReservationsByUserId(Integer userId) {
        List<Reservation> userReservations = reservationRepository.findAllByCustomer_Id(userId);

        LocalDateTime currentDateTime = LocalDateTime.now();

        List<Reservation> futureReservations = userReservations.stream()
                .filter(reservation ->addDuration(reservation).isAfter(currentDateTime))
                .collect(Collectors.toList());

        return reservationMapper.mapReservationsToDto(futureReservations);
    }


    public List<ReservationDto> getFutureReservationsByAdminId(Integer adminId) {
        List<Reservation> userReservations = reservationRepository.findAllByCompanyAdmin_Id(adminId);

        LocalDateTime currentDateTime = LocalDateTime.now();

        List<Reservation> futureReservations = userReservations.stream()
                .filter(reservation ->addDuration(reservation).isAfter(currentDateTime) && !reservation.getStatus().equals(ReservationStatus.PickedUp))
                .collect(Collectors.toList());

        return reservationMapper.mapReservationsToDto(futureReservations);
    }


    public List<ReservationDto> getPastReservationsByUserId(Integer userId) {
        List<Reservation> userReservations = reservationRepository.findAllByCustomer_Id(userId);
        Customer customer = customerService.getById(userId);

        LocalDateTime currentDateTime = LocalDateTime.now();

        List<Reservation> pastReservations = userReservations.stream()
                .filter(reservation -> addDuration(reservation).isBefore(currentDateTime))
                .collect(Collectors.toList());



        return reservationMapper.mapReservationsToDto(pastReservations);
    }


    public List<ReservationDto> getPastReservationsByAdminId(Integer adminId) {
        List<Reservation> userReservations = reservationRepository.findAllByCompanyAdmin_Id(adminId);
        //CompanyAdmin admin = companyAdminService.getById(adminId);

        LocalDateTime currentDateTime = LocalDateTime.now();

        List<Reservation> pastReservations = userReservations.stream()
                .filter(reservation -> addDuration(reservation).isBefore(currentDateTime) && !reservation.getStatus().equals(ReservationStatus.PickedUp))
                .collect(Collectors.toList());

        return reservationMapper.mapReservationsToDto(pastReservations);
    }

    public LocalDateTime addDuration(Reservation res){
        return res.getDateTime().plusHours(res.getDuration());
    }



    public boolean isEquipmentReservedByAdmin(Equipment equipment, Company company) {
        List<CompanyAdmin> companyAdmins = new ArrayList<>(company.getCompanyAdmin());

        for (CompanyAdmin companyAdmin : companyAdmins) {
            List<Reservation> reservations = reservationRepository.findByCompanyAdminId(companyAdmin.getId());

            for (Reservation reservation : reservations) {
                for(ReservationEquipment reservationEquipment: reservation.getReservationOfEquipments())
                if (Objects.equals(reservationEquipment.getEquipment().getName(), equipment.getName())) {
                    // Equipment is reserved by this admin
                    return true;
                }
            }
        }

        // Equipment is not reserved by any admin
        return false;
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ReservationDto createReservation(ReservationDto reservationDto) {
        Customer customer = customerService.getById(reservationDto.getCustomerId());

        CompanyAdmin companyAdmin = companyAdminService.getById(reservationDto.getCompanyAdminId());
        Reservation reservation = new Reservation(reservationDto.getId(), reservationDto.getDateTime(), reservationDto.getDuration(), reservationDto.getGrade(), customer, companyAdmin);

        Reservation reservation1 = reservationRepository.save(reservation);
        Set<ReservationEquipment> reservationEquipments = new HashSet<>();
        for (int i = 0; i < reservationDto.getReservationOfEquipments().size(); i++) {
            String eqName = reservationDto.getReservationOfEquipments().get(i).getEquipmentName();
            Equipment equipment = _equipmentRepository.findEquipmentByName(eqName);
            if (equipment != null) {
                ReservationEquipment resEq = new ReservationEquipment(reservationDto.getReservationOfEquipments().get(i).getQuantity());
                resEq.setReservation(reservation1);
                resEq.setEquipment(equipment);
                reservationEquipments.add(resEq);
                _resEqRepository.save(resEq);
            }
        }

        reservation1.setReservationOfEquipments(reservationEquipments);
        return new ReservationDto(reservation1);
    }

    @Transactional
    public void updateReservation(Reservation reservation){
        // Fetch the existing Reservation entity
        Reservation existingReservation = reservationRepository.findById(reservation.getId()).orElse(null);

        if (existingReservation != null) {
            // Update the existing entity with the changes
            existingReservation.setStatus(reservation.getStatus());
            // Update other fields as needed

            // Save the updated entity
            reservationRepository.save(existingReservation);
        } else {
            // Handle the case where the entity with the given ID is not found
            // (Optional: throw an exception, log a warning, etc.)
        }
    }

    //@Transactional
    public ReservationCancelationDTO cancelReservation(ReservationDto reservationDto){

        Reservation reservation = reservationMapper.mapDtoToEntity(reservationDto);
        reservation.setStatus(ReservationStatus.Cancelled);
        updateReservation(reservation);

        Customer customer = customerService.getById(reservationDto.getCustomerId());
        if(reservation.getDateTime().isBefore(LocalDateTime.now().plusHours(24))){
            customer.setPenaltyPoints(customer.getPenaltyPoints()+2);
        }
        else{
            customer.setPenaltyPoints(customer.getPenaltyPoints()+1);
        }
        customerRepository.save(customer);
        List<AvailableDate> availableDates = availableDateRepository.findAvailableDateByAdmin_Id(reservation.getCompanyAdmin().getId());
        AvailableDate canceledDate = availableDates.stream()
                .filter(availableDate -> availableDate.getStartTime().isEqual(reservation.getDateTime()) )
                .findFirst()
                .orElse(null);

        canceledDate.setTaken(false);
        availableDateRepository.save(canceledDate);

        ReservationCancelationDTO cancelationDTO = new ReservationCancelationDTO(reservation.getId(), customer.getPenaltyPoints());
        return cancelationDTO;
    }

    //@Transactional
    public ReservationCancelationDTO cancelReservationQR(ReservationDto reservationDto){

        Reservation reservation = reservationMapper.mapDtoToEntity(reservationDto);
        reservation.setStatus(ReservationStatus.Cancelled);
        updateReservation(reservation);

        Customer customer = customerService.getById(reservationDto.getCustomerId());

        customer.setPenaltyPoints(customer.getPenaltyPoints()+2);
        customerRepository.save(customer);

        /*
        List<AvailableDate> availableDates = availableDateRepository.findAvailableDateByAdmin_Id(reservation.getCompanyAdmin().getId());
        AvailableDate canceledDate = availableDates.stream()
                .filter(availableDate -> availableDate.getStartTime().isEqual(reservation.getDateTime()) )
                .findFirst()
                .orElse(null);

        if (canceledDate != null) {
            canceledDate.setTaken(false);
            availableDateRepository.save(canceledDate);
        } else {
            // Handle the case where canceledDate is null
            System.out.println("No matching AvailableDate found for reservation time: " + reservation.getDateTime());
        }

         */

        ReservationCancelationDTO cancelationDTO = new ReservationCancelationDTO(reservation.getId(), customer.getPenaltyPoints());
        return cancelationDTO;
    }

    @Transactional
    public ReservationCancelationDTO pickUpReservation(ReservationDto reservationDto){
        try{
            Reservation reservation = reservationMapper.mapDtoToEntity(reservationDto);
            reservation.setStatus(ReservationStatus.PickedUp);
            updateReservation(reservation);

            Integer companyAdminId = reservation.getCompanyAdmin().getId();
            CompanyAdmin companyAdmin = companyAdminService.getById(companyAdminId);
            Integer companyId = companyAdmin.getCompanyId();
            Company company = companyService.getById(companyId);

            for (int i = 0; i < reservationDto.getReservationOfEquipments().size(); i++) {
                String eqName = reservationDto.getReservationOfEquipments().get(i).getEquipmentName();
                Equipment equipment = _equipmentRepository.findEquipmentByName(eqName);
                CompanyEquipment companyEquipment = companyEquipmentRepository.findByCompanyAndEquipment(company, equipment);

                if(companyEquipment != null){
                    Integer currentQuantity = companyEquipment.getQuantity();
                    Integer newQuantity = currentQuantity - reservationDto.getReservationOfEquipments().get(i).getQuantity();
                    if(newQuantity >= 0){
                        companyEquipment.setQuantity(newQuantity);
                        companyEquipmentRepository.save(companyEquipment);
                    }else{
                        // Rollback the transaction as newQuantity is negative
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        throw new IllegalArgumentException("New quantity cannot be negative.");
                    }
                }
            }

            Customer customer = customerService.getById(reservationDto.getCustomerId());

            ReservationCancelationDTO cancelationDTO = new ReservationCancelationDTO(reservation.getId(), customer.getPenaltyPoints());
            return cancelationDTO;
        }
        catch(OptimisticLockException e){
            throw new RuntimeException("Conflict occurred while trying to pick up a reservation.", e);
        }
    }
}
