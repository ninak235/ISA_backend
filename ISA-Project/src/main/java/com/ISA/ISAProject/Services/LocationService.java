package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.ComplaintDto;
import com.ISA.ISAProject.Dto.LocationDto;
import com.ISA.ISAProject.Mapper.LocationMapper;
import com.ISA.ISAProject.Model.Complaint;
import com.ISA.ISAProject.Model.Location;
import com.ISA.ISAProject.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository _locationRepository;

    @Autowired
    private final LocationMapper _locationMapper;

    public LocationService(LocationMapper locationMapper) {
        _locationMapper = locationMapper;
    }

    @Transactional
    public LocationDto createLocation(LocationDto locationDto){
        Location location = _locationMapper.dtoToLocation(locationDto);
        _locationRepository.save(location);
        return  new LocationDto(location);
    }
}
