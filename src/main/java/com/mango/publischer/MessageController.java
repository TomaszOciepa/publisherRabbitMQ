package com.mango.publischer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {
    private final RabbitTemplate rabbitTemplate;

    public MessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/message")
    public String sendMessage(@RequestParam String message){
        rabbitTemplate.convertAndSend("stau", message);
        return "Wrzucono wiadomość na RabbitMQ!!";
    }

    @PostMapping("/notification")
    public String sendNotification(@RequestBody Notification notification){
        rabbitTemplate.convertAndSend("stau", notification);
        return "Notifikacja wysłana";
    }
}
