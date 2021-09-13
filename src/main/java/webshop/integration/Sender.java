package webshop.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import webshop.dto.EventDTO;

@Service
public class Sender {
    @Autowired
    private KafkaTemplate<String, EventDTO> kafkaTemplate;

    public void send(EventDTO eventDTO){kafkaTemplate.send("cartUpdated",eventDTO);}
}
