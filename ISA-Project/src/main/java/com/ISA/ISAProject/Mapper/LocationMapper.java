package com.ISA.ISAProject.Mapper;

import com.ISA.ISAProject.Dto.CompanyDto;
import com.ISA.ISAProject.Dto.ComplaintDto;
import com.ISA.ISAProject.Dto.LocationDto;
import com.ISA.ISAProject.Dto.UserDto;
import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.Complaint;
import com.ISA.ISAProject.Model.Location;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocationMapper {
    private final ModelMapper mapper;

    @Autowired
    public LocationMapper(ModelMapper modelMapper){this.mapper = modelMapper;}

    public Location dtoToLocation(LocationDto dto){
        Location location = mapper.map(dto, Location.class);
        return location;
    }

    public LocationDto mapLocationToDto(Location location) {
        return mapper.map(location, LocationDto.class);
    }
}
