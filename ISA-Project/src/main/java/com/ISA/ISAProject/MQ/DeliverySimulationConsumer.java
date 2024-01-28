package com.ISA.ISAProject.MQ;

import com.ISA.ISAProject.Dto.ContractDto;
import com.ISA.ISAProject.Dto.LocationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DeliverySimulationConsumer {

    private static final Logger log = LoggerFactory.getLogger(SimulationConsumer.class);


    @RabbitListener(queues="delivery-simulator")
    public void handler(String contract) {
        log.info("Consumer -- " + contract);
    }

    private final ObjectMapper objectMapper = new ObjectMapper();


    @RabbitListener(queues = "delivery-simulator")
    public void handle(byte[] messageBytes) {
        try {
            ContractDto contract = objectMapper.readValue(messageBytes, ContractDto.class);
            System.out.println("Received contract: " + contract.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
