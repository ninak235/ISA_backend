package com.ISA.ISAProject.Mapper;

import com.ISA.ISAProject.Dto.AvailableDateDto;
import com.ISA.ISAProject.Model.AvailableDate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AvailableDateMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public AvailableDateMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AvailableDateDto mapAvailableDateToDto(AvailableDate availableDate) {
        return modelMapper.map(availableDate, AvailableDateDto.class);
    }

    public List<AvailableDateDto> mapAvailableDatesToDto(List<AvailableDate> availableDates) {
        return availableDates.stream()
                .map(this::mapAvailableDateToDto)
                .collect(Collectors.toList());
    }
}
