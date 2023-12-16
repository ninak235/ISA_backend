package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.AvailableDateDto;
import com.ISA.ISAProject.Mapper.AvailableDateMapper;
import com.ISA.ISAProject.Model.AvailableDate;
import com.ISA.ISAProject.Repository.AvailableDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AvailableDateService {

    @Autowired
    private AvailableDateRepository availableDateRepository;

    @Autowired
    private AvailableDateMapper availableDateMapper;

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
        return availableDateMapper.mapAvailableDatesToDto(availableDates);
    }

    // Additional methods for updating, confirming, and other reservation-related actions
}
