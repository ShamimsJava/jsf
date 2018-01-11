<%-- 
    Document   : index
    Created on : Jan 9, 2018, 6:58:58 PM
    Author     : Shamim
--%>
<%@ page import="java.io.*,java.util.*, java.sql.*"%>  
<%@ page import="javax.servlet.http.*, javax.servlet.*" %>  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
    <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"  
                       url="jdbc:mysql://localhost/loginjdbc"  
                       user="root"  password="root"/>   
    <sql:query dataSource="${db}" var="rs">  
        SELECT * from user;  
    </sql:query>  
    <c:forEach var="table" items="${rs.rows}">
        <c:out value="${table.id}"/>
    </c:forEach>
</body>
</html>
