package com.spasov.homework6.servlet;

import com.spasov.homework6.entity.UserType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

@WebServlet(name = "АuthorizationServlet", value = "/authorization-servlet")
public class АuthorizationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pass");
        HttpSession session = req.getSession();
        if ("admin".equals(login) && "password".equals(password)) {
            String loginSession = (String) session.getAttribute("loginSession");
            if (loginSession == null) {
                session.setAttribute("loginSession", login);
            }
            String passwordSession = (String) session.getAttribute("passwordSession");
            if (passwordSession == null) {
                session.setAttribute("passwordSession", password);
            }
            Date entryTime = (Date) session.getAttribute("entryTime");
            if (entryTime == null) {
                session.setAttribute("entryTime", new Date());
            }
            UserType userType = (UserType) session.getAttribute("userType");
            if (userType == null || userType == UserType.GUEST) {
                session.setAttribute("userType", UserType.USER);
            }
            req.getRequestDispatcher("/user-page/text.jsp").forward(req, resp);
        } else {
            UserType userType = (UserType) session.getAttribute("userType");
            if (userType == null || userType == UserType.USER) {
                session.setAttribute("userType", UserType.GUEST);
            }
            req.setAttribute("notAuth", "<h2>Неверный логин или пароль</h2>");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }


    }
}
