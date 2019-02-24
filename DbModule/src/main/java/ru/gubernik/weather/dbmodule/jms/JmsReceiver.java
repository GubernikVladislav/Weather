package ru.gubernik.weather.dbmodule.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gubernik.weather.dbmodule.service.WeatherService;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Слушатель Jms очереди jms/queue/weather
 */
@Component
public class JmsReceiver implements MessageListener {

    @Autowired
    private WeatherService weatherService;

    public JmsReceiver(){

    }

    @Override
    public void onMessage(Message message) {

        if(message == null){
            return;
        }

        try {
            if(message.isBodyAssignableTo(WeatherDto.class)) {
                WeatherDto weather = message.getBody(WeatherDto.class);
                weatherService.save(weather);
            }else {
                throw new RuntimeException("Incorrect message body type");
            }
        } catch (JMSException e) {
            throw new RuntimeException("Jms message error", e);
        }
    }
}
