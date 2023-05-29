<%--
  Created by IntelliJ IDEA.
  User: Наташа
  Date: 27.05.2023
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calc</title>
</head>
<body>
<form action="/operation/calc" method="post">
  <input type="text" placeholder="Num 1" name="num1">
  <input type="text" placeholder="Num 2" name="num2">
  <input type="text" placeholder="Type" name="type">
  <button>Submit</button>
</form>
<p>Result = ${result}</p>
<form action="/operation/calc/history">
  <button>History</button>
</form>
</body>
</html>
