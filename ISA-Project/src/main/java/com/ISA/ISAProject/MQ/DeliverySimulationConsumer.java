package com.ISA.ISAProject.MQ;

import com.ISA.ISAProject.Dto.ContractDto;
import com.ISA.ISAProject.Dto.LocationDto;
import com.ISA.ISAProject.Model.Contract;
import com.ISA.ISAProject.Services.ContractService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DeliverySimulationConsumer {

    private static final Logger log = LoggerFactory.getLogger(SimulationConsumer.class);

    @Autowired
    private ContractService _contractService;


    @RabbitListener(queues="delivery-simulator")
    public void handler(String contractStr) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = objectMapper.readValue(contractStr, Map.class);

            List<String> equipmentNames = (List<String>) map.get("equipmentNames");
            List<Integer> quantity = (List<Integer>) map.get("quantity");
            LocalDateTime exactDeliveryTime = LocalDateTime.parse((String) map.get("exactDeliveryTime"));
            String hospitalName = (String) map.get("hospitalName");
            double hospitalAddressLong = (Double) map.get("hospitalAddressLong");
            double hospitalAddressLat = (Double) map.get("hospitalAddressLat");
            String companyName = (String) map.get("companyName");

            ContractDto contract = new ContractDto(exactDeliveryTime, hospitalName, companyName, hospitalAddressLat, hospitalAddressLong);
            contract.setEquipmentNames(equipmentNames);
            contract.setQuantity(quantity);

            log.info("Consumer:" + contract);

            _contractService.createContract(contract);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
