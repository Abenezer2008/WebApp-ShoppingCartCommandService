package webshop.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DeleteEventSender {
    @Autowired
    private KafkaTemplate<String, String > kafkaTemplate;
    public void send(String cartId){kafkaTemplate.send("cartDeleted",cartId);}
}
