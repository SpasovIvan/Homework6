<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 31.03.2024
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Text</title>
</head>
<body>
<a href="../">Вернуться на главную</a>
<h3>Вопросительные предложения из текста</h3>

<h5>${URLDecoder.decode(cookie.text.value)}</h5>
</body>
</html>
