package ru.gubernik.weather.yahoo.jms;

import ru.gubernik.weather.yahoo.service.module.ModuleService;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
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

    @Resource
    private MessageDrivenContext mdc;

    @Inject
    private ModuleService moduleService;

    @Override
    public void onMessage(Message message) {
        try {
            if(message.isBodyAssignableTo(String.class)) {
                String location = message.getBody(String.class);
                moduleService.request(location);
            }
        } catch (JMSException e) {
            throw new RuntimeException("Jms message error", e);
        }
    }
}
