package ru.gubernik.weather.yahoo.jms;

import ru.gubernik.weather.model.Weather;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.Message;
import java.util.Queue;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class JmsSenderImpl implements JmsSender {

    @Resource(mappedName = "java:jms/queue/weather")
    private Queue queue;

    private final JMSContext context;

    private Message message;

    @Inject
    public JmsSenderImpl(JMSContext context) {
        this.context = context;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(Weather weather) {
        message = context.createObjectMessage(weather);
        context.createProducer().send((Destination) queue, message);
    }
}
