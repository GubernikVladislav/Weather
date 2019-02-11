package ru.gubernik.weather.admin.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JmsSenderImplTest {

    @Mock
    private JMSContext context;
    @Mock
    private Queue queue;

    @InjectMocks
    private JmsSenderImpl sender;

    @Test
    public void sendTest(){

        String message = "Message";
        JMSProducer producer = mock(JMSProducer.class);

        when(context.createProducer()).thenReturn(producer);

        sender.send(message);

        verify(context, atLeast(1)).createProducer();
        verify(producer, atLeast(1)).send(queue, message);
    }
}