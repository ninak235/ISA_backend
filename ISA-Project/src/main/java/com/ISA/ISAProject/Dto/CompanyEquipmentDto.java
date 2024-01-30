package com.ISA.ISAProject.Dto;

import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.Location;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyEquipmentDto {
    private Integer id;
    private String name;
    private LocationDto locationDto;
    private String description;
    private String grade;

    private List<EquipmentDto> equipmentSet;

    private List<CompanyAdminBasicDto> adminsSet;

    // Add default constructor
    public CompanyEquipmentDto() {
    }

    public CompanyEquipmentDto(Company company) {
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


      Hibernate.initialize(company.getCompanyAdmin());
      Hibernate.initialize(company.getEquipmentList());

        this.equipmentSet = new ArrayList<>(company.getEquipmentList().stream()
                .map(EquipmentDto::new)
                .collect(Collectors.toList()));

        this.adminsSet = new ArrayList<>(company.getCompanyAdmin().stream()
                .map(companyAdmin -> new CompanyAdminBasicDto(companyAdmin.getUser()))
                .collect(Collectors.toList()));


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

   public List<EquipmentDto> getEquipmentSet() {
        return equipmentSet;
    }

    public void setEquipmentSet(List<EquipmentDto> equipmentSet) {
        this.equipmentSet = equipmentSet;
    }


    public List<CompanyAdminBasicDto> getAdminsSet() {
        return adminsSet;
    }

    public void setAdminsSet(List<CompanyAdminBasicDto> adminsSet) {
        this.adminsSet = adminsSet;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
