package com.ISA.ISAProject.Mapper;

import com.ISA.ISAProject.Dto.EquipmentDto;
import com.ISA.ISAProject.Enum.TypeOfUser;
import com.ISA.ISAProject.Model.Equipment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EquipmentMappper {
    private final ModelMapper mapper;

    @Autowired
    public EquipmentMappper(ModelMapper modelMapper){this.mapper = modelMapper;}

    public List<EquipmentDto> mapEquipmentsToDto(List<Equipment> equipments){
        return equipments.stream()
                .map(EquipmentDto::new)
                .collect(Collectors.toList());
    }

    public Equipment dtoToEquipment(EquipmentDto dto){
        Equipment equipment = mapper.map(dto, Equipment.class);
        return equipment;
    }
}
