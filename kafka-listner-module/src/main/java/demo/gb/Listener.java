package demo.gb;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Listener {
    @KafkaListener(topics = "mytopic", groupId = "group_id")
    public void listen(String message) {
        System.out.println( "Received Message: " + message);
    }
}
