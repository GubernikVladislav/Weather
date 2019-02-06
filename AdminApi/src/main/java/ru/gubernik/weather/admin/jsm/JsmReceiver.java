package ru.gubernik.weather.admin.jsm;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.ArrayList;
import java.util.List;

@MessageDriven(name = "Receiver", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "java:/jms/queue/messages"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge")})
public class JsmReceiver implements MessageListener {

    private List<String> messages = new ArrayList<String>();

    public void onMessage(Message message) {
        try {
            messages.add(message.getBody(String.class));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public List<String> getMessageList(){
        return messages;
    }
}
