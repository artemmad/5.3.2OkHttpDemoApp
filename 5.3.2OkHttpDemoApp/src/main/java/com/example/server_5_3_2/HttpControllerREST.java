package com.example.server_5_3_2;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HttpControllerREST extends HttpServlet {

    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("lastname") != null || request.getParameter("firstname") != null)
            if (!request.getParameter("lastname").equals("")  && !request.getParameter("firstname").equals("")) {
                String lastname = request.getParameter("lastname");
                String firstname = request.getParameter("firstname");
                firstname = firstname.substring(0, 1);
                return lastname + " " + firstname + ".";
            } else
                return "No POST data lastname or firstname";
        return "<h1>Greetings from Spring Boot!</h1>"
                + "<form name='test' method='post' action=''>"
                + "<p><b>Фамилия:</b><br>"
                + "<input type='text' name='lastname' size='40'></p>"
                + "<p><b>Имя:</b><br>"
                + "<input type='text' name='firstname' size='40'></p>"
                + "<p><input type='submit' value='Отправить'></p>" + " </form>";
    }
}