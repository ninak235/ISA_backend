package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.AvailableDateDto;
import com.ISA.ISAProject.Dto.EquipmentDto;
import com.ISA.ISAProject.Mapper.AvailableDateMapper;
import com.ISA.ISAProject.Model.*;
import com.ISA.ISAProject.Repository.AvailableDateRepository;
import com.ISA.ISAProject.Repository.CompanyAdminRepository;
import com.ISA.ISAProject.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    public List<AvailableDateDto> getAllAvailableDays() {
        List<AvailableDate> availableDates = availableDateRepository.findAll();
        return availableDateMapper.mapAvailableDatesToDto(availableDates);
    }

    @Transactional
    public AvailableDateDto getAvailableDateById(Integer availableDateId) {
        Optional<AvailableDate> optionalAvailableDate = availableDateRepository.findById(availableDateId);
        return optionalAvailableDate.map(availableDateMapper::mapAvailableDateToDto).orElse(null);
    }

    @Transactional
    public List<AvailableDateDto> getAllAvailableDaysByCompanyId(Integer companyId){
        List<AvailableDate> availableDates = availableDateRepository.findAvailableDatesByAdmin_Company_Id(companyId);
        List<AvailableDate> futureAvailableDates = new ArrayList<>();
        LocalDateTime currentDateTime = LocalDateTime.now();

        for (AvailableDate date: availableDates) {
            if (date.getStartTime().isAfter(currentDateTime)) {
                futureAvailableDates.add(date);
            }
        }
        return availableDateMapper.mapAvailableDatesToDto(futureAvailableDates);
    }

    @Transactional
    public List<AvailableDateDto> getExtraAvailableDaysByCompanyId(Integer companyId, String selectedDate) {
        Instant instant = parseSelectedDate(selectedDate);
        if (instant != null) {
            Company company = companyRepository.findById(companyId).orElse(null);
            if (company != null) {
                List<LocalDateTime> allDateTimes = generateAllDateTimes(instant, company);

                List<AvailableDate> newDates = generateAvailableDates(allDateTimes, company);

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

    private List<AvailableDate> generateAvailableDates(List<LocalDateTime> allDateTimes, Company company) {
        List<AvailableDate> newDates = new ArrayList<>();
        for (LocalDateTime dateTime : allDateTimes) {
            CompanyAdmin availableCompanyAdmin = getAvailableCompanyAdmin(dateTime, company.getId());
            AvailableDate availableDate = new AvailableDate(availableCompanyAdmin, dateTime, Duration.ofMinutes(30));
            newDates.add(availableDate);
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
                Duration thirtyMinutes = Duration.ofMinutes(30);
                LocalDateTime sum = startTime.plus(thirtyMinutes);
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

    @Transactional
    public AvailableDateDto createAvailableDate(AvailableDateDto availableDateDto) {
        AvailableDate availableDate = availableDateRepository.save(availableDateMapper.mapDtoToEntity((availableDateDto)));
        return new AvailableDateDto(availableDate);
    }

    @Transactional
    public AvailableDate update(AvailableDateDto availableDateDto) {
        AvailableDate availableDate = availableDateRepository.findById(availableDateDto.getId()).orElse(null);
        availableDate.setTaken(availableDateDto.getTaken());
        availableDateRepository.save(availableDate);
        return availableDate;
    }
}
