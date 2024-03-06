package com.ISA.ISAProject.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ResponseService {

    private static final Logger log = LoggerFactory.getLogger(ResponseService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendToExchange(String exchange, String routingkey, String response){
        log.info("Sending> ... Message=[ " + response + " ] Exchange=[" + exchange + "] RoutingKey=[" + routingkey + "]");
        this.rabbitTemplate.convertAndSend(exchange, routingkey, response);
    }
}
