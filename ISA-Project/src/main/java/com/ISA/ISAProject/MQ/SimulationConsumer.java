package com.ISA.ISAProject.MQ;

import com.ISA.ISAProject.Dto.LocationDto;
import com.ISA.ISAProject.Dto.LocationSimulatorDto;
import com.ISA.ISAProject.Model.Location;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
@Component
public class SimulationConsumer {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    private static final Logger log = LoggerFactory.getLogger(SimulationConsumer.class);
    public List<LocationSimulatorDto> locations = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues="location-simulator")
    @MessageMapping("/send/location")
    public void handler(LocationSimulatorDto location) throws JsonProcessingException {
        //log.info("Consumer> " + location);
        locations.add(location);
        String jsonLocation = objectMapper.writeValueAsString(location);
        log.info(jsonLocation);
        simpMessagingTemplate.convertAndSend("/socket-publisher", jsonLocation);
    }
}
