package ru.gubernik.weather.admin.jsm;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.Queue;
import javax.jms.JMSContext;



/**
 * Класс для отправки JMS сообщений в очередь на сервере
 */
@ApplicationScoped
public class JmsSenderImpl implements JsmSender {

    /**
     * JSM очередь полученная от сервера по JNDI
     */
    @Resource(mappedName = "java:/jms/queue/messages")
    private Queue queue;

    @Inject
    private JMSContext context;

    /**
     * Отправка полученного сообщения в JSM очередь
     * @param message
     */
    public void send(String message){
        context.createProducer().send(queue, message);
    }
}
