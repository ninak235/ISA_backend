package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.LoyalityProgramDto;
import com.ISA.ISAProject.Mapper.LoyalityProgramMapper;
import com.ISA.ISAProject.Model.LoyalityProgram;
import com.ISA.ISAProject.Repository.LoyalityProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class LoyalityProgramService {

    @Autowired
    private LoyalityProgramRepository _loyalityProgramRepository;
    @Autowired
    private final LoyalityProgramMapper _loyalityProgramMapper;

    public LoyalityProgramService(LoyalityProgramMapper loyalityProgramMapper) {
        _loyalityProgramMapper = loyalityProgramMapper;
    }

    public LoyalityProgramDto defineLoyalityProgram(LoyalityProgramDto dto){
        LoyalityProgram loyalityProgram = _loyalityProgramMapper.dtoToLoyalityProgram(dto);
        _loyalityProgramRepository.save(loyalityProgram);

        return new LoyalityProgramDto(loyalityProgram);
    }
}
