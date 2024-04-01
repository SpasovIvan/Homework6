<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="text_questions-servlet">Вопросительные предложения</a>
<form method="post" action="authorization-servlet">
    <div>
        <label>
            <input name="login" type="text">
        </label>
    </div>
    <div>
        <label>
            <input name="pass" type="password">
        </label>
    </div>
    <div>
        <label>
            <input type="submit">
        </label>
    </div>
</form>
${notAuth}
<h5>${URLDecoder.decode(cookie.text.value)}</h5>
</body>
</html>