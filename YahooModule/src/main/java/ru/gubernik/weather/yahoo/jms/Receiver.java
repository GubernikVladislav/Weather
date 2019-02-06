package ru.gubernik.weather.yahoo.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "java:jboss/exported/jms/queue/city", containerFactory = "myFactory")
    public void receive(String message) {
        System.out.println(message);
    }
}
