package ru.gubernik.weather.yahoo.jms;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.gubernik.weather.yahoo.service.module.ModuleService;

import javax.jms.JMSException;
import javax.jms.Message;

import static org.mockito.Mockito.*;

/**
 * Тестирование слушателя сообщений модуля Yahoo
 */
@RunWith(MockitoJUnitRunner.class)
public class JmsReceiverTest {

    @Mock
    private ModuleService moduleService;

    @InjectMocks
    private JmsReceiver receiver;

    @Before
    public void checkNull(){
        Assert.assertNotNull(receiver);
        Assert.assertNotNull(moduleService);
    }

    /**
     * Проверка при правильном переданном сообщении
     * @throws JMSException
     */
    @Test
    public void receiveTest() throws JMSException {

        Message message = mock(Message.class);
        String location = "Moscow";

        when(message.isBodyAssignableTo(String.class)).thenReturn(true);
        when(message.getBody(String.class)).thenReturn(location);

        receiver.onMessage(message);

        verify(moduleService, atLeast(1)).request(location);
    }

    /**
     * Проверка при неправильном сообщении
     * @throws JMSException
     */
    @Test
    public void noReceiveTest() throws JMSException {
        Message message = mock(Message.class);

        when(message.isBodyAssignableTo(String.class)).thenReturn(false);

        receiver.onMessage(message);

        verifyZeroInteractions(moduleService);
    }
}
