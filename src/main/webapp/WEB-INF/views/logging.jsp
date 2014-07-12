<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loging</title>
</head>
<body>
<h1>LOGIN DEL BLOG</h1>
<c:url var="urlAutentificar" value="/autentificar" />
<c:if test="${errorLogin }">
El usuario o password son incorrectos
</c:if>
<div>
LOGINS FALLIDOS ${sessionScope.loginsFallidos}
</div>
<div>
SESION, EL CONTENIDO DE VARIABLE ES: <c:out value="${sessionScope.variable }" />
</div>
<div>
<p>Email introducido: ${SPRING_SECURITY_LAST_EXCEPTION.authentication.name}</p>
<p>Password Introducido: ${SPRING_SECURITY_LAST_EXCEPTION.authentication.credentials}</p>
</div>
<form action="${urlAutentificar }" method="post">
   <div>
        <label>Email:</label><input type="text" name="email" />
   </div>
   <div>
         <label>Password:</label><input type="password" name="password" />
   </div>
   <input type="submit" value="Enviar" />
</form>

</body>

</html>