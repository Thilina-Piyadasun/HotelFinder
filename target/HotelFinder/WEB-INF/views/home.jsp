<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

</head>
<body>

<p>Type in the word to continue</p>

<%--rendering base64 encoded byte arry to string--%>
<img src="data:image/jpeg;base64,${image}" width="100" height="50">

<form action="home" method="post">
    <input name="captchaUserInput" type="text" autocomplete="off" />
    <input type="submit" />
</form>

</body>
</html>