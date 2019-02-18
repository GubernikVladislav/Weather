package ru.gubernik.weather.admin.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Тестирование JmsSender
 */
@RunWith(MockitoJUnitRunner.class)
public class JmsSenderImplTest {

    @Mock
    private JMSContext context;
    @Mock
    private Queue queue;

    @InjectMocks
    private JmsSenderImpl sender;

    /**
     * Проверка отправки сообщения
     */
    @Test
    public void sendTest(){

        String message = "Message";
        JMSProducer producer = mock(JMSProducer.class);

        when(context.createProducer()).thenReturn(producer);

        sender.send(message);

        verify(context, atLeast(1)).createProducer();
        verify(producer, atLeast(1)).send(queue, message);
    }

    /**
     * Проверка при null сообщении. Сообщение не должно быть отправленно
     */
    @Test
    public void nullSendTest(){
        String message = null;
        JMSProducer producer = mock(JMSProducer.class);

        when(context.createProducer()).thenReturn(producer);

        sender.send(message);

        verifyZeroInteractions(context);
        verifyZeroInteractions(producer);
    }

    /**
     * Проверка при пустом сообщении
     */
    @Test
    public void emptyMessageTest(){
        String message = "";
        JMSProducer producer = mock(JMSProducer.class);

        when(context.createProducer()).thenReturn(producer);

        sender.send(message);

        verifyZeroInteractions(context);
        verifyZeroInteractions(producer);
    }
}