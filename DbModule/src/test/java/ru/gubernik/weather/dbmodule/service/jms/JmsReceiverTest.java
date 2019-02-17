package ru.gubernik.weather.dbmodule.service.jms;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.gubernik.weather.dbmodule.jms.JmsReceiver;
import ru.gubernik.weather.dbmodule.service.WeatherService;
import ru.gubernik.weather.dbserviceapi.model.WeatherDto;

import javax.jms.JMSException;
import javax.jms.Message;

import static org.mockito.Mockito.*;

/**
 * Тестирование слушателя Jms очереди
 */
@RunWith(MockitoJUnitRunner.class)
public class JmsReceiverTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private JmsReceiver jmsReceiver;

    /**
     * Проверка инъекции
     */
    @Test
    public void checkNull(){
        Assert.assertNotNull(weatherService);
        Assert.assertNotNull(jmsReceiver);
    }

    /**
     * Проверка получения сообщения
     * @throws JMSException
     */
    @Test
    public void receiveTest() throws JMSException {
        Message message = mock(Message.class);
        WeatherDto weather = mock(WeatherDto.class);

        when(message.isBodyAssignableTo(WeatherDto.class)).thenReturn(true);
        when(message.getBody(WeatherDto.class)).thenReturn(weather);

        jmsReceiver.onMessage(message);

        verify(message, times(1)).isBodyAssignableTo(WeatherDto.class);
        verify(message, times(1)).getBody(WeatherDto.class);
        verify(weatherService, times(1)).save(weather);
    }

    /**
     * Проверка пропуска сообщения, если сообщение не приводится к нужному типу
     * @throws JMSException
     */
    @Test
    public void noReceiveTest() throws JMSException {
        Message message = mock(Message.class);
        WeatherDto weather = mock(WeatherDto.class);

        when(message.isBodyAssignableTo(WeatherDto.class)).thenReturn(false);

        jmsReceiver.onMessage(message);

        verifyZeroInteractions(weatherService);
    }
}
