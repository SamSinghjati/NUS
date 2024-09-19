package com.luv2shopdev.ecommerce.kafka.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luv2shopdev.ecommerce.kafka.config.KafkaSenderExample;
//import com.luv2shopdev.ecommerce.kafka.producer.MessageProducer;

@RestController
@RequestMapping("/api/kafka")

public class KafkaController {

   // @Autowired
  //  private MessageProducer messageProducer;
    
    @Autowired
    private KafkaSenderExample example;

    @PostMapping("/send")
    public String sendMessage(@RequestParam("message") String message) {
    	example.sendMessage(message,"reflectoring-2");
    	
        return "Message sent: " + message;
    }

}