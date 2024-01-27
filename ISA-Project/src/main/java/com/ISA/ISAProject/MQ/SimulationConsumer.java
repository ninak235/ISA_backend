package com.ISA.ISAProject.MQ;

import com.ISA.ISAProject.Dto.LocationDto;
import com.ISA.ISAProject.Model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimulationConsumer {

    private static final Logger log = LoggerFactory.getLogger(SimulationConsumer.class);
    public List<LocationDto> locations = new ArrayList<>();

    @RabbitListener(queues="location-simulator")
    public void handler(LocationDto location){
        log.info("Consumer> " + location);
        locations.add(location);
    }

}
