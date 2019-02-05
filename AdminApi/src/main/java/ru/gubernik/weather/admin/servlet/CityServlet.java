package ru.gubernik.weather.admin.servlet;

import ru.gubernik.weather.admin.jsm.JmsSender;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет обработки страницы index.jps
 */
public class CityServlet extends HttpServlet {

    @Inject
    private JmsSender jmsSender;

    /**
     * получение запроса со страницы index.jsp и обработка запроса
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String cityName = req.getParameter("cityName");
        jmsSender.send(cityName);
    }
}
