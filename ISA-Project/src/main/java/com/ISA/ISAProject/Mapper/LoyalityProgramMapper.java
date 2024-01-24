package com.ISA.ISAProject.Mapper;

import com.ISA.ISAProject.Dto.ComplaintDto;
import com.ISA.ISAProject.Dto.LoyalityProgramDto;
import com.ISA.ISAProject.Model.Complaint;
import com.ISA.ISAProject.Model.LoyalityProgram;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoyalityProgramMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public LoyalityProgramMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public LoyalityProgram dtoToLoyalityProgram(LoyalityProgramDto dto){
        LoyalityProgram loyalityProgram = modelMapper.map(dto, LoyalityProgram.class);
        return loyalityProgram;
    }
}
