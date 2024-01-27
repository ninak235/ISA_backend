package com.Isa.SimulatorLokacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api" )
public class SimulationProducerController {

    @Autowired
    private SimulationProducer producer;

    @PostMapping(value="/{exchange}/{queue}",consumes = "application/json")
    public ResponseEntity<String> sendMessageToExchange(@PathVariable("exchange") String exchange, @PathVariable("queue") String queue, @RequestBody List<LocationBean> locations) {
        producer.sendToExchange(exchange, queue, locations);
        return ResponseEntity.ok().build();
    }
}
