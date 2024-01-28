package com.ISA.ISAProject.MQ;

import com.ISA.ISAProject.Dto.ContractDto;
import com.ISA.ISAProject.Dto.LocationDto;
import com.ISA.ISAProject.Model.Location;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimulationConsumer {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    private static final Logger log = LoggerFactory.getLogger(SimulationConsumer.class);
    public List<LocationDto> locations = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues="location-simulator")
    public void handler(LocationDto location) throws JsonProcessingException {
        //log.info("Consumer> " + location);
        locations.add(location);
        String jsonLocation = objectMapper.writeValueAsString(location);
        log.info(jsonLocation);
        simpMessagingTemplate.convertAndSend("/socket-publisher/", jsonLocation);
    }

    @RabbitListener(queues="delivery-simulator")
    public void handler(ContractDto contract) throws JsonProcessingException {
        String jsonContract = objectMapper.writeValueAsString(contract);
        log.info(jsonContract);
        simpMessagingTemplate.convertAndSend("/socket-publisher/", jsonContract);
    }

}
