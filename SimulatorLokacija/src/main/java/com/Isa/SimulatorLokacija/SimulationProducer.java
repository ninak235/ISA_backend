package com.Isa.SimulatorLokacija;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimulationProducer {

    private static final Logger log = LoggerFactory.getLogger(SimulationProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RoutingService routingService;


    public void sendToExchange(String exchange, String routingkey, List<LocationBean> locations){
        //log.info("Sending> ... Message=[ " + locations + " ] Exchange=[" + exchange + "] RoutingKey=[" + routingkey + "]");
        List<LocationBean> pointsBetween = new ArrayList<>();
        pointsBetween.add(locations.get(0));
        pointsBetween.addAll(routingService.getPointsBetween(locations.get(0), locations.get(1)));
        pointsBetween.add(locations.get(1));
        for(LocationBean point: pointsBetween) {
            try {
                this.rabbitTemplate.convertAndSend(exchange, routingkey, point);
                log.info(" Message=" + point);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Thread sleep interrupted", e);
            }
        }
    }


}
