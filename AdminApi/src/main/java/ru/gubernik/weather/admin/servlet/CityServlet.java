package ru.gubernik.weather.admin.servlet;

import ru.gubernik.weather.admin.jsm.JmsSenderImpl;

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
    private JmsSenderImpl jmsSender;

    /**
     * получение запроса со страницы index.jsp и обработка запроса
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("cityName") != null) {
            String cityName = req.getParameter("cityName");
            if(!cityName.isEmpty()) {
                jmsSender.send(cityName);
            }else {
                req.setAttribute("exception", "City name must be not null");
            }
        }

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
