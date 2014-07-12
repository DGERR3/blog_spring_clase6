<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EL POST</title>
</head>
<body>

   <h1>POST ACTUAL</h1>
   
   <c:out value="${nombre}" />
   
   
   <h2>${post.titulo}</h2>
   
   <p>${post.contenido}</p>
   
   <c:forEach var="comentario" items="${post.comentarios }">
   <h4><c:out value="${comentario.comentarista }"/>dice:</h4>
   <p><c:out value="${comentario.comentario }"/></p>
   </c:forEach>

</body>
</html>