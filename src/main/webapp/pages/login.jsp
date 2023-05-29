<%--
  Created by IntelliJ IDEA.
  User: Наташа
  Date: 27.05.2023
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<s:form action="/user/login" method="post" modelAttribute="newLogin">

    <s:input path="username" placeholder="Username"/>
<br>
    <s:errors path="username"/>
<br>
    <s:input path="password" placeholder="Password"/>
<br>
    <s:errors path="password"/>
<br>
<s:button>Submit</s:button>
</s:form>

