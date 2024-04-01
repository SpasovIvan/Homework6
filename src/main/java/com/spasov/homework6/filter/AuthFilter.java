package com.spasov.homework6.filter;

import com.spasov.homework6.entity.UserType;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/user-page/*", "/text_questions-servlet"}, servletNames = {"text_questions-servlet"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        HttpSession session = ((HttpServletRequest)request).getSession();
        UserType userType = (UserType) session.getAttribute("userType");
        if (userType == null || userType == UserType.GUEST) {
            userType = UserType.GUEST;
            session.setAttribute("userType", userType);
            servletRequest.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        if (userType == UserType.USER) {
            servletRequest.getRequestDispatcher("/user-page/text.jsp").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
