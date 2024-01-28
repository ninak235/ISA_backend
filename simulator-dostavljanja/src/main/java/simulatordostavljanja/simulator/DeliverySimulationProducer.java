package simulatordostavljanja.simulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeliverySimulationProducer {

    private static final Logger log = LoggerFactory.getLogger(DeliverySimulationProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendToExchange(String exchange, String routingkey, ContractBean contract){
        rabbitTemplate.convertAndSend(exchange, routingkey, contract);
        System.out.println("Poruka poslata u razmjenu (" + exchange + ") s ključem rutiranja (" + routingkey + ")");
    }

}
