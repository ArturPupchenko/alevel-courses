package com.alevel.courses.jeebox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "first-servlet", urlPatterns = "/")
public class FirstServlet extends HttpServlet {
//    Реализовать веб-приложение, которое принимает GET запросы,
//    и хранит в памяти список уникальных ip адресов, сделавших к нему запрос,
//    и соответствующих значений http-заголовка User-Agent
//    При запросе выдает html документ с текущим списком
//    (при этом IP и User-Agent пользователя, сделавшего запрос - выделить жирным текстом - html тег <b></b>)
//
//    Пример элемента списка:
//
//    127.0.0.1 :: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:71.0) Firefox/71.0

    private static final Logger log = LoggerFactory.getLogger(FirstServlet.class);

    private Map<String, String> ipsWithUserAgents = new ConcurrentHashMap<>();

    @Override
    public void init() {
        log.info("First Servlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ipsWithUserAgents.put(req.getRemoteAddr(), req.getHeader("User-Agent"));

        PrintWriter responseBody = resp.getWriter();

        resp.setContentType("text/html");
        for (Map.Entry<String, String> visit : ipsWithUserAgents.entrySet()) {
            if ((visit.getKey() == req.getRemoteAddr()) && (visit.getValue() == req.getHeader("user-agent"))) {
                responseBody.println("<b>" + visit.getKey() + " :: " + visit.getValue() + "</b>");
            } else responseBody.println("<h3>" + visit.getKey() + " :: " + visit.getValue() + "</h3>");
        }
    }

    @Override
    public void destroy() {
        log.info("First Servlet destroyed");
    }
}
