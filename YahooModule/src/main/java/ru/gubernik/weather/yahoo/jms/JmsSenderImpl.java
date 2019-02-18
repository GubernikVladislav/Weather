package ru.gubernik.weather.yahoo.jms;

import ru.gubernik.weather.dbserviceapi.model.WeatherDto;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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

    @Inject
    private JMSContext context;

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(WeatherDto weather) {

        if (weather != null) {
            Message message = context.createObjectMessage(weather);
            context.createProducer().send(queue, message);
        }else {
            throw new RuntimeException("Send message error: weather cannot be null");
        }
    }
}
