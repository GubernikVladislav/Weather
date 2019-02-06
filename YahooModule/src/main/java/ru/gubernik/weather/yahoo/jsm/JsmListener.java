package ru.gubernik.weather.yahoo.jsm;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class JsmListener {

    @JmsListener(containerFactory = "myFactory", destination = "java:/jms/topic/city")
    public void pringMes(String s){
        System.out.println(s);
    }
}
