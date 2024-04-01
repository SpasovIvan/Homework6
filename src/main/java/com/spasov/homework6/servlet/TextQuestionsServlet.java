package com.spasov.homework6.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URLEncoder;

@WebServlet(name = "TextQuestionsServlet", value = "/text_questions-servlet")
public class TextQuestionsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream textStream = TextQuestionsServlet.class.getResourceAsStream("/text.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(textStream));
        StringBuilder textBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            textBuilder.append(line);
        }
        String text = textBuilder.toString();
        String subtext;
        StringBuilder questionLines = new StringBuilder();
        while (text.contains("?")) {
            int indexOf = text.indexOf("?")+1;
            subtext = text.substring(0, indexOf);
            while (subtext.contains(".")) {
                int indexOfPoint = subtext.indexOf(".")+1;
                subtext = subtext.substring(indexOfPoint);
            }
            questionLines.append(subtext);
            text = text.substring(indexOf);
        }
        text = URLEncoder.encode(questionLines.toString());
        Cookie cookie = new Cookie("text", text);
        resp.addCookie(cookie);
        req.getRequestDispatcher("/user-page/text.jsp").forward(req, resp);
    }
}
