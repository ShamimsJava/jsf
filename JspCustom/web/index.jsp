<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="mytags" prefix="shamim" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>index.jsp</title>
    </head>
    <body>
        <h2>Current Date and Time is : <shamim:today/> </h2>
        <h2>Cube of number 5 : <shamim:cube number="5"/> </h2>
        <h2><shamim:copyright/> </h2>
    </body>
</html>
