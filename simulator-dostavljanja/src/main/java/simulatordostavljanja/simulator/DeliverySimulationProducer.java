package simulatordostavljanja.simulator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    @Autowired
    private ObjectMapper objectMapper;

    // log.info("Sending> ... Message=[ " + contract + " ] Exchange=[" + exchange + "] RoutingKey=[" + routingkey + "]");


    public void sendToExchange(String exchange, String routingkey, ContractBean contract) {
        try {
            log.info("Sending> ... Message=[ " + contract + " ] Exchange=[" + exchange + "] RoutingKey=[" + routingkey + "]");
            String jsonContract = objectMapper.writeValueAsString(contract);
            rabbitTemplate.convertAndSend(exchange, routingkey, jsonContract);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
