package com.ISA.ISAProject.Mapper;

import com.ISA.ISAProject.Dto.CompanyDto;
import com.ISA.ISAProject.Dto.CompanyEquipmentDto;
import com.ISA.ISAProject.Dto.ComplaintDto;
import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.Complaint;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ComplaintMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public ComplaintMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<ComplaintDto> mapComplaintToDto(List<Complaint> complaints) {
        return complaints.stream()
                .map(ComplaintDto::new)
                .collect(Collectors.toList());
    }

    public Complaint dtoToComplaint(ComplaintDto dto){
        Complaint complaint = modelMapper.map(dto, Complaint.class);
        return complaint;
    }
}
