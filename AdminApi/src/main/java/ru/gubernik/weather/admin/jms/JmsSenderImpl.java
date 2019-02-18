package ru.gubernik.weather.admin.jms;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.Queue;
import javax.jms.JMSContext;


/**
 * Класс для отправки JMS сообщений в очередь на сервере
 */
@ApplicationScoped
public class JmsSenderImpl implements JmsSender {

    /**
     * JSM очередь полученная от сервера по JNDI
     */
    @Resource(mappedName = "java:jboss/exported/jms/queue/city")
    private Queue queue;

    private final JMSContext context;

    @Inject
    public JmsSenderImpl(JMSContext context) {
        this.context = context;
    }

    /**
     * Конструктор для тестов
     */
    protected JmsSenderImpl(JMSContext context, Queue queue){
        this.context = context;
        this.queue = queue;
    }

    /**
     * Отправка полученного сообщения в JSM очередь
     * @param message - текст сообщения
     */
    public void send(String message){
        if (message.isEmpty()){
            return;
        }
        context.createProducer().send(queue, message);
    }
}
