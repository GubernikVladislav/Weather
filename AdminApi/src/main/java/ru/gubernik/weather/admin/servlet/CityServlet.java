package ru.gubernik.weather.admin.servlet;

import ru.gubernik.weather.admin.jms.JmsSender;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Сервлет обработки страницы index.jps
 */
public class CityServlet extends HttpServlet {

    private final JmsSender jmsSender;
    private Pattern pattern = Pattern.compile("^[a-zA-Z]+[\\-]?[a-zA-Z]+[\\-]?[a-zA-Z]+$");

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
            setForward(req, resp, "City name must be not null");
            return;
        }

        Matcher matcher = pattern.matcher(cityName);

        if(!matcher.matches()){
            setForward(req, resp, "City name must contains only letters or -");
            return;
        }

        jmsSender.send(cityName);
        setForward(req, resp, "Success");
    }

    /**
     * Перезагрузка .jsp страницы
     */
    private void setForward(HttpServletRequest req, HttpServletResponse resp, String attribute) throws ServletException, IOException {
        req.setAttribute("exception", attribute);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
