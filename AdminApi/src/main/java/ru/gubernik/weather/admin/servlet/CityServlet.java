package ru.gubernik.weather.admin.servlet;

import ru.gubernik.weather.admin.jms.JmsSender;

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

    private final JmsSender jmsSender;

    @Inject
    public CityServlet(JmsSender jmsSender) {
        this.jmsSender = jmsSender;
    }

    /**
     * получение запроса со страницы index.jsp и обработка запроса
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String cityName = req.getParameter("cityName");

        if(cityName == null || cityName.isEmpty()){
            req.setAttribute("exception", "City name must be not null");
            setForward(req, resp);
            return;
        }else if(!cityName.matches("^[a-zA-Z]+[\\-]?[a-zA-Z]+[\\-]?[a-zA-Z]+$")){
            req.setAttribute("exception", "City name must contains only letters ");
            setForward(req, resp);
            return;
        }

        jmsSender.send(cityName);
        setForward(req, resp);
    }

    /**
     * Перезагрузка .jsp страницы
     */
    private void setForward(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
