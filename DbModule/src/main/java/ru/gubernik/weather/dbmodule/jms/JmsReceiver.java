package ru.gubernik.weather.dbmodule.jms;

import ru.gubernik.weather.dbmodule.service.WeatherService;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Слушатель Jms очереди jms/queue/weather
 */
@MessageDriven(name = "GbJmsReceiver", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "java:jboss/exported/jms/queue/weather"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge")

})
public class JmsReceiver implements MessageListener {

    @Resource
    private MessageDrivenContext mdc;

    @Inject
    private WeatherService weatherService;

    public JmsReceiver(){

    }

    @Override
    public void onMessage(Message message) {
        try {
            WeatherDto weather = message.getBody(WeatherDto.class);
            weatherService.save(weather);
        } catch (JMSException e) {
            throw new RuntimeException("Jms message error", e);
        }
    }
}
