package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.AvailableDateDto;
import com.ISA.ISAProject.Dto.EquipmentDto;
import com.ISA.ISAProject.Mapper.AvailableDateMapper;
import com.ISA.ISAProject.Model.*;
import com.ISA.ISAProject.Repository.AvailableDateRepository;
import com.ISA.ISAProject.Repository.CompanyAdminRepository;
import com.ISA.ISAProject.Repository.CompanyRepository;
import com.ISA.ISAProject.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OptimisticLockException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvailableDateService {

    @Autowired
    private AvailableDateRepository availableDateRepository;

    @Autowired
    private AvailableDateMapper availableDateMapper;

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyAdminRepository companyAdminRepository;
    @Autowired
    ReservationRepository reservationRepository;

    public List<AvailableDateDto> getAllAvailableDays() {
        List<AvailableDate> availableDates = availableDateRepository.findAll();
        return availableDateMapper.mapAvailableDatesToDto(availableDates);
    }


    public AvailableDateDto getAvailableDateById(Integer availableDateId) {
        Optional<AvailableDate> optionalAvailableDate = availableDateRepository.findById(availableDateId);
        return optionalAvailableDate.map(availableDateMapper::mapAvailableDateToDto).orElse(null);
    }


    public List<AvailableDateDto> getAllAvailableDaysByCompanyId(Integer companyId,Integer userId){
        List<AvailableDate> availableDates = availableDateRepository.findAvailableDatesByAdmin_Company_Id(companyId);
        List<AvailableDate> futureAvailableDates = new ArrayList<>();
        LocalDateTime currentDateTime = LocalDateTime.now();

        for (AvailableDate date: availableDates) {
            if (date.getStartTime().isAfter(currentDateTime)) {
                futureAvailableDates.add(date);
            }
        }

        List<AvailableDate> filteredAvailableDates = filterForCustomerCancelation(userId, companyId, futureAvailableDates);
        if (filteredAvailableDates != null) {
            futureAvailableDates = filteredAvailableDates;
        }
        /*
        List<Reservation> customerReservations = reservationRepository.findAllByCustomer_Id(userId);
        if(customerReservations != null){
            futureAvailableDates = futureAvailableDates.stream()
                    .filter(date -> customerReservations.stream()
                            .noneMatch(reservation -> reservation.getDateTime().isEqual(date.getStartTime()) &&
                                    reservation.getCompanyAdmin().getCompany().getId().equals(companyId)))
                    .collect(Collectors.toList());
        }
        */
        return availableDateMapper.mapAvailableDatesToDto(futureAvailableDates);
    }

    private List<AvailableDate>  filterForCustomerCancelation(Integer userId,Integer companyId, List<AvailableDate> futureAvailableDates){
        List<Reservation> customerReservations = reservationRepository.findAllByCustomer_Id(userId);
        if(customerReservations != null){
            futureAvailableDates = futureAvailableDates.stream()
                    .filter(date -> customerReservations.stream()
                            .noneMatch(reservation -> reservation.getDateTime().isEqual(date.getStartTime()) &&
                                    reservation.getCompanyAdmin().getCompany().getId().equals(companyId)))
                    .collect(Collectors.toList());
            return futureAvailableDates;
        }
        return null;
    }


    public List<AvailableDateDto> getAllAvailableDaysByAdminId(Integer adminId){
        List<AvailableDate> availableDates = availableDateRepository.findAvailableDateByAdmin_Id(adminId);
        return availableDateMapper.mapAvailableDatesToDto(availableDates);
    }

    /*
    @Transactional
    public List<AvailableDateDto> getAllAvailableDaysByCompanyAndAdminId(Integer companyId, Integer companyAdminId){
        List<AvailableDate> availableDates = availableDateRepository.findAvailableDatesByAdmin_Company_Id(companyId);
        availableDates = availableDates.stream()
                .filter(date -> date.getAdmin().getId().equals(companyAdminId))
                .collect(Collectors.toList());

        return availableDateMapper.mapAvailableDatesToDto(availableDates);
    }
    */



    public List<AvailableDateDto> getExtraAvailableDaysByCompanyId(Integer companyId, String selectedDate,Integer userId) {
        Instant instant = parseSelectedDate(selectedDate);
        if (instant != null) {
            Company company = companyRepository.findById(companyId).orElse(null);
            if (company != null) {
                List<LocalDateTime> allDateTimes = generateAllDateTimes(instant, company);

                List<AvailableDate> newDates = generateAvailableDates(allDateTimes, company,userId);

                return availableDateMapper.mapAvailableDatesToDto(newDates);
            }
        }

        return null;
    }


    public List<AvailableDateDto> getExtraAvailableDaysByCompanyIdAndAdminId(String companyName, Integer companyAdminId, String selectedDate) {
        Instant instant = parseSelectedDate(selectedDate);
        if (instant != null) {
            Company company = companyRepository.findCompanyByName(companyName);
            if (company != null) {
                List<LocalDateTime> allDateTimes = generateAllDateTimes(instant, company);

                List<AvailableDate> newDates = generateAvailableDatesForAdmin(allDateTimes, company, companyAdminId);

                return availableDateMapper.mapAvailableDatesToDto(newDates);
            }
        }

        return null;
    }

    private Instant parseSelectedDate(String selectedDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date parsedDate = dateFormat.parse(selectedDate);
            return parsedDate.toInstant();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<LocalDateTime> generateAllDateTimes(Instant instant, Company company) {
        List<LocalDateTime> allDateTimes = new ArrayList<>();
        LocalTime currentTime = company.getStartWorkingTime();

        while (currentTime.isBefore(company.getEndWorkingTime())) {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                    .with(currentTime);
            allDateTimes.add(localDateTime);
            currentTime = currentTime.plusHours(3);
        }

        return allDateTimes;
    }

    private List<AvailableDate> generateAvailableDates(List<LocalDateTime> allDateTimes, Company company,Integer userId) {
        List<AvailableDate> newDates = new ArrayList<>();
        for (LocalDateTime dateTime : allDateTimes) {
            if (getAvailableCompanyAdmin(dateTime, company.getId()) != null){

                CompanyAdmin availableCompanyAdmin = getAvailableCompanyAdmin(dateTime, company.getId());
                AvailableDate availableDate = new AvailableDate(availableCompanyAdmin, dateTime, 3600);
                newDates.add(availableDate);
            }
        }
        List<AvailableDate> filteredList = filterForCustomerCancelation(userId,company.getId(),newDates);
        return filteredList;
    }

    private List<AvailableDate> generateAvailableDatesForAdmin(List<LocalDateTime> allDateTimes, Company company, Integer companyAdminId) {
        List<AvailableDate> newDates = new ArrayList<>();
        Optional<CompanyAdmin> tempAdmin = companyAdminRepository.findById(companyAdminId);
        if(tempAdmin != null){
            CompanyAdmin admin = tempAdmin.get();
            for (LocalDateTime dateTime : allDateTimes) {
                //CompanyAdmin availableCompanyAdmin = getAvailableCompanyAdmin(dateTime, company.getId());
                if(!isAdminBusy(dateTime, companyAdminId)){
                    AvailableDate availableDate = new AvailableDate(admin, dateTime, 3600);
                    newDates.add(availableDate);
                    System.out.println("New date added: " + availableDate);
                }
            }
        }

        return newDates;
    }


    private CompanyAdmin getAvailableCompanyAdmin(LocalDateTime dateTime, int companyId) {

        List<AvailableDate> availableDates = availableDateRepository.findAvailableDatesByAdmin_Company_Id(companyId);
        List<CompanyAdmin> allCompanyAdmins = companyAdminRepository.findAllByCompany_Id(companyId);
        List<Integer> busyUserIds = new ArrayList<>();
        for (AvailableDate availableDate: availableDates){
            LocalDateTime startTime = availableDate.getStartTime();
            LocalDate date1 = dateTime.toLocalDate();
            LocalDate date2 = startTime.toLocalDate();
            if( availableDate.getTaken() == true && date1.equals(date2) ) {
                LocalDateTime sum = startTime.plusHours(3);
                if (dateTime.compareTo(startTime) >= 0 && dateTime.compareTo(sum) <= 0) {//|| sum2.compareTo(startTime) >= 0) {

                    busyUserIds.add(availableDate.getAdmin().getId());
                }
            }
        }
        for (CompanyAdmin companyAdmin: allCompanyAdmins){
            if (! busyUserIds.contains(companyAdmin.getId())){
                return companyAdmin;
            }
        }
        return null;
    }

    private Boolean isAdminBusy(LocalDateTime dateTime, int adminId) {
        List<AvailableDate> availableDates = availableDateRepository.findAvailableDateByAdmin_Id(adminId);
        Optional<CompanyAdmin> optAdmin = companyAdminRepository.findById(adminId);
        if(optAdmin != null){
            CompanyAdmin admin = optAdmin.get();
            for(AvailableDate date: availableDates){
                LocalDateTime startTime = date.getStartTime();
                LocalDateTime sum = startTime.plusMinutes(179);
                if(dateTime.compareTo(startTime) >= 0 && dateTime.compareTo(sum) <= 0){
                    return true;
                }
            }
            return false;
        }
        return false;

    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public AvailableDateDto createAvailableDate(AvailableDateDto availableDateDto) {
        Optional<AvailableDate> duplicateDate = availableDateRepository.findAvailableDateByAdmin_IdAndStartTime(availableDateDto.getAdminId(), availableDateDto.getStartTime());
        if (!duplicateDate.isPresent()){
            AvailableDate availableDate = availableDateRepository.save(availableDateMapper.mapDtoToEntity((availableDateDto)));
            return new AvailableDateDto(availableDate);

        }else{
            AvailableDate existingAvailableDate = duplicateDate.get(); // Extract AvailableDate from Optional
            return new AvailableDateDto(existingAvailableDate);
        }
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public AvailableDate update(AvailableDateDto availableDateDto) {
        try{
            AvailableDate availableDate = availableDateRepository.findById(availableDateDto.getId()).orElse(null);
            availableDate.setTaken(availableDateDto.getTaken());
            availableDateRepository.save(availableDate);
            return availableDate;
        }
        catch(OptimisticLockException e){
            throw new RuntimeException("Conflict occurred while updating the available date.", e);
        }

    }
}
