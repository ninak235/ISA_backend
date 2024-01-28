package simulatordostavljanja.simulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/delivery" )
public class DeliverySimulationController {

    @Autowired
    private DeliverySimulationProducer producer;

    @PostMapping(value="/{exchange}/{queue}", consumes = "application/json")
    public ResponseEntity<String> sendMessageToExchange(@PathVariable("exchange") String exchange, @PathVariable("queue") String queue,  @RequestBody ContractBean contract) {
        producer.sendToExchange(exchange, queue, contract);
        return ResponseEntity.ok().build();
    }

}
