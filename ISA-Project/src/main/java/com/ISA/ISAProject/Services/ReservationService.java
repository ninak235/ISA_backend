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

import javax.transaction.Transactional;
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
    private CompanyEquipmentRepository companyEquipmentRepository;


    @Transactional
    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservationMapper.mapReservationsToDto(reservations);
    }

    @Transactional
    public List<ReservationDto> getAllReservationsByCompanyAdminId(Integer companyAdminId) {
        List<Reservation> reservations = reservationRepository.findByCompanyAdminId(companyAdminId);
        return reservationMapper.mapReservationsToDto(reservations);
    }

    @Transactional
    public ReservationDto getReservationById(Integer reservationId) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        return optionalReservation.map(reservationMapper::mapReservationToDto).orElse(null);
    }

    @Transactional
    public List<ReservationDto> getFutureReservationsByUserId(Integer userId) {
        List<Reservation> userReservations = reservationRepository.findAllByCustomer_Id(userId);

        LocalDateTime currentDateTime = LocalDateTime.now();

        List<Reservation> futureReservations = userReservations.stream()
                .filter(reservation ->addDuration(reservation).isAfter(currentDateTime))
                .collect(Collectors.toList());

        return reservationMapper.mapReservationsToDto(futureReservations);
    }

    @Transactional
    public List<ReservationDto> getFutureReservationsByAdminId(Integer adminId) {
        List<Reservation> userReservations = reservationRepository.findAllByCompanyAdmin_Id(adminId);

        LocalDateTime currentDateTime = LocalDateTime.now();

        List<Reservation> futureReservations = userReservations.stream()
                .filter(reservation ->addDuration(reservation).isAfter(currentDateTime) && !reservation.getStatus().equals(ReservationStatus.PickedUp))
                .collect(Collectors.toList());

        return reservationMapper.mapReservationsToDto(futureReservations);
    }

    @Transactional
    public List<ReservationDto> getPastReservationsByUserId(Integer userId) {
        List<Reservation> userReservations = reservationRepository.findAllByCustomer_Id(userId);
        Customer customer = customerService.getById(userId);

        LocalDateTime currentDateTime = LocalDateTime.now();

        List<Reservation> pastReservations = userReservations.stream()
                .filter(reservation -> addDuration(reservation).isBefore(currentDateTime))
                .collect(Collectors.toList());



        return reservationMapper.mapReservationsToDto(pastReservations);
    }

    @Transactional
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


    @Transactional
    public boolean isEquipmentReservedByAdmin(Equipment equipment, Company company) {
        List<CompanyAdmin> companyAdmins = new ArrayList<>(company.getCompanyAdmin());

        for (CompanyAdmin companyAdmin : companyAdmins) {
            List<Reservation> reservations = reservationRepository.findByCompanyAdminId(companyAdmin.getId());

            for (Reservation reservation : reservations) {
                if (reservation.getReservationEquipments().contains(equipment)) {
                    // Equipment is reserved by this admin
                    return true;
                }
            }
        }

        // Equipment is not reserved by any admin
        return false;
    }


    @Transactional
    public ReservationDto createReservation(ReservationDto reservationDto) {
        Customer customer = customerService.getById(reservationDto.getCustomerId());

        CompanyAdmin companyAdmin = companyAdminService.getById(reservationDto.getCompanyAdminId());
        Reservation reservation = reservationMapper.mapDtoToEntity(reservationDto);

        reservation.setCompanyAdmin(companyAdmin);
        reservation.setCustomer(customer);

        //Reservation reservation1 = new Reservation(reservation);
        Reservation reservation1 = reservationRepository.save(reservation);
        return new ReservationDto(reservation1);
    }

    public void updateReservation(Reservation reservation){
        reservationRepository.save(reservation);
    }

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

    public ReservationCancelationDTO pickUpReservation(ReservationDto reservationDto){

        Reservation reservation = reservationMapper.mapDtoToEntity(reservationDto);
        reservation.setStatus(ReservationStatus.PickedUp);
        updateReservation(reservation);

        CompanyAdmin companyAdmin = reservation.getCompanyAdmin();
        Company company = companyAdmin.getCompany();
        Set<Equipment> equipmentList = reservation.getReservationEquipments();

        for (Equipment e : equipmentList) {
            Optional<CompanyEquipment> companyEquipmentOptional = companyEquipmentRepository.findByCompanyAndEquipment(company, e);

            companyEquipmentOptional.ifPresent(companyEquipment -> {
                Integer currentQuantity = companyEquipment.getQuantity();
                Integer newQuantity = currentQuantity - 1;

                companyEquipmentRepository.updateQuantity(company, e, newQuantity);

                System.out.println("UPDATE" + newQuantity);
            });
        }



        Customer customer = customerService.getById(reservationDto.getCustomerId());

    /*
        List<AvailableDate> availableDates = availableDateRepository.findAvailableDateByAdmin_Id(reservation.getCompanyAdmin().getId());
        AvailableDate canceledDate = availableDates.stream()
                .filter(availableDate -> availableDate.getStartTime().isEqual(reservation.getDateTime()) )
                .findFirst()
                .orElse(null);

        canceledDate.setTaken(false);
        availableDateRepository.save(canceledDate);
        */

        ReservationCancelationDTO cancelationDTO = new ReservationCancelationDTO(reservation.getId(), customer.getPenaltyPoints());
        return cancelationDTO;
    }
}
