package ru.gubernik.weather.yahoo.jms;

import ru.gubernik.weather.dbserviceapi.model.WeatherDto;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Queue;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class JmsSenderImpl implements JmsSender {

    @Resource(mappedName = "java:jboss/exported/jms/queue/weather")
    private Queue queue;

    private final JMSContext context;

    @Inject
    public JmsSenderImpl(JMSContext context) {
        this.context = context;
    }

    /**
     * Конструктор для тестов
     * @param context
     * @param queue
     */
    protected  JmsSenderImpl(JMSContext context, Queue queue){
        this.context = context;
        this.queue = queue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(WeatherDto weather) {
        if (weather != null) {
            Message message = context.createObjectMessage(weather);
            context.createProducer().send((Destination) queue, message);
        }
    }
}
