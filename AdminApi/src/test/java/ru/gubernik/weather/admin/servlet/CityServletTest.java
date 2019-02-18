package ru.gubernik.weather.admin.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.gubernik.weather.admin.jms.JmsSender;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CityServletTest {

    private String attribute = "exception";
    private String forwargUrl = "/index.jsp";

    @Mock
    private JmsSender sender;

    @InjectMocks
    private CityServlet servlet;

    /**
     * Проверка метода doPost при правильно введеных данных
     * @throws ServletException иссключение метода doPost
     * @throws IOException иссключение метода doPost
     */
    @Test
    public void servletTest() throws ServletException, IOException {

        String message = "Moscow";

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("cityName")).thenReturn(message);
        when(request.getRequestDispatcher(forwargUrl)).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(sender, atLeast(1)).send(message);
        verify(request, atLeast(1)).setAttribute(attribute, "Success");
        verify(request, times(1)).getRequestDispatcher(forwargUrl);
        verify(dispatcher).forward(request, response);
    }

    /**
     * Проверка при пустом названии города
     * @throws ServletException иссключение метода doPost
     * @throws IOException иссключение метода doPost
     */
    @Test
    public void nullCityNameTest() throws ServletException, IOException {

        String message = "";

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("cityName")).thenReturn(message);
        when(request.getRequestDispatcher(forwargUrl)).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verifyZeroInteractions(sender);
        verify(request, atLeast(1)).setAttribute(attribute, "City name must be not null");
        verify(request, atLeast(1)).getRequestDispatcher(forwargUrl);
        verify(dispatcher, atLeast(1)).forward(request, response);
    }

    @Test
    public void incorrectCityNameTest() throws ServletException, IOException {

        String message = "3333";

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("cityName")).thenReturn(message);
        when(request.getRequestDispatcher(forwargUrl)).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verifyZeroInteractions(sender);
        verify(request, atLeast(1)).setAttribute(attribute, "City name must contains only letters or -");
        verify(request, atLeast(1)).getRequestDispatcher(forwargUrl);
        verify(dispatcher, atLeast(1)).forward(request, response);
    }
}
