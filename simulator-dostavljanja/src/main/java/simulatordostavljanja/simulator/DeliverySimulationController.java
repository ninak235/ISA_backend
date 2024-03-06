package simulatordostavljanja.simulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= "/api/delivery" )
public class DeliverySimulationController {

    @Autowired
    private DeliverySimulationProducer producer;

    private static final Logger log = LoggerFactory.getLogger(DeliverySimulationProducer.class);



    @PostMapping(value="/{exchange}/{queue}", consumes = "application/json")
    public ResponseEntity<String> sendMessageToExchange(@PathVariable("exchange") String exchange, @PathVariable("queue") String queue,  @RequestBody ContractBean contract) {
         producer.sendToExchange(exchange, queue, contract);
        return ResponseEntity.ok().build();
    }


    @CrossOrigin
    @PostMapping(value="/canceled/{hospitalName}")
    public void canceled(@PathVariable("hospitalName") String hospitalName) {
        log.info("!!!!!!!! DELIVERY OF HOSPITAL:  " + hospitalName + " IS CANCELED !!!!!!!!.");
    }

}
