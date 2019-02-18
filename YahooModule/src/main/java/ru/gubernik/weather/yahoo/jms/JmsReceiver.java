package ru.gubernik.weather.yahoo.jms;

import ru.gubernik.weather.yahoo.service.module.ModuleService;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Слушатель Jms очереди java:/jms/queue/city
 */
@MessageDriven(name = "JmsReceiver", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "java:/jms/queue/city"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge")})
public class JmsReceiver implements MessageListener {

    @Inject
    private ModuleService moduleService;

    public JmsReceiver(){
    }

    @Override
    public void onMessage(Message message) {

        if(message == null){
            return;
        }

        try {
            if(message.isBodyAssignableTo(String.class)) {
                String location = message.getBody(String.class);
                moduleService.request(location);
            }else {
                throw new RuntimeException("Incorrect message body type");
            }
        } catch (JMSException e) {
            throw new RuntimeException("Jms message error", e);
        }
    }
}
