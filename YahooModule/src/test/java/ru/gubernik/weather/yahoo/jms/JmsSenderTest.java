package ru.gubernik.weather.yahoo.jms;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Тестирование отправки jms сообщений
 */
@RunWith(MockitoJUnitRunner.class)
public class JmsSenderTest {

    @Mock
    private JMSContext context;
    @Mock
    private Queue queue;

    @InjectMocks
    private JmsSenderImpl sender;

    /**
     * ПРоверка инъекций
     */
    @Before
    public void checkNull(){
        Assert.assertNotNull(sender);
        Assert.assertNotNull(context);
        Assert.assertNotNull(queue);
    }

    /**
     * Тестирование отправки сообщения в Jms очередь
     */
    @Test
    public void senderTest(){

        ObjectMessage message = mock(ObjectMessage.class);
        WeatherDto weather = mock(WeatherDto.class);
        JMSProducer producer = mock(JMSProducer.class);

        when(context.createObjectMessage(weather)).thenReturn(message);
        when(context.createProducer()).thenReturn(producer);

        sender.send(weather);

        verify(context, atLeast(1)).createObjectMessage(weather);
        verify(context, atLeast(1)).createProducer();
        verify(producer, atLeast(1)).send(queue, message);
    }

    /**
     * Проверка игнорирования null Значений
     */
    @Test
    public void noSendTest(){
        WeatherDto weatherDto = null;

        try {
            sender.send(weatherDto);
        }catch (RuntimeException e){
            assertEquals(e.getMessage(), "Send message error: weather cannot be null");
        }
        verifyZeroInteractions(context);
    }
}
