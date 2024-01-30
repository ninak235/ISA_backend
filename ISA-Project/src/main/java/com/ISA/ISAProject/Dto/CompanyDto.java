package com.ISA.ISAProject.Dto;

import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.Location;

import java.time.LocalTime;


public class CompanyDto {
    private Integer id;
    private String name;
    private LocationDto locationDto;
    private String description;
    private String grade;

    //private List<EquipmentDto> equipmentSet;

    // Add default constructor
    public CompanyDto() {
    }

    public CompanyDto(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        Location location = company.getLocation();

        if (location != null) {
            LocationDto locationDto = new LocationDto();
            locationDto.setId(location.getId());
            locationDto.setAddress(location.getAddress());
            locationDto.setCity(location.getCity());
            locationDto.setCountry(location.getCountry());
            locationDto.setLatitude(location.getLatitude());
            locationDto.setLongitude(location.getLongitude());

            this.locationDto = locationDto;
        }

        this.description = company.getDescription();
        this.grade = company.getGrade();



    /*    Hibernate.initialize(company.getEquipment());

        this.equipmentSet = new ArrayList<>(company.getEquipment().stream()
                .map(EquipmentDto::new)
                .collect(Collectors.toList()));*/

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationDto getLocationDto() {
        return locationDto;
    }

    public void setLocationDto(LocationDto locationDto) {
        this.locationDto = locationDto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    /*  public List<EquipmentDto> getEquipmentSet() {
        return equipmentSet;
    }

    public void setEquipmentSet(List<EquipmentDto> equipmentSet) {
        this.equipmentSet = equipmentSet;
    }*/

}
