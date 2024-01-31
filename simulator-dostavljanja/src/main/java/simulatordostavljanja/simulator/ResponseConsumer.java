package simulatordostavljanja.simulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseConsumer {
    private static final Logger log = LoggerFactory.getLogger(ResponseConsumer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues="response")
    public void handler(String response){
        log.info("Consumer> " + response);
    }
}
