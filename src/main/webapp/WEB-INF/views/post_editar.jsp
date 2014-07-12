<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edita Post</title>
</head>
<body>
<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
<div>
<button>Destruir Aplicacion</button>
</div>
</sec:authorize>
<!-- SI VIENE LA BANDERA ACTUALIZADO PINTAMOS UN MENSAJE -->
<c:if test="${Actualizado}">
<h3>DATOS GUARDADOS</h3>
</c:if>
   <c:url var="urlGuardar" value="/blog/guardar"/>
   
   <form:form action="${urlGuardar}"  method="post" commandName="post">
   <form:input type="hidden" path="id"  />
     <div>
      <!--  <label>Titulo</label> -->
      <label><spring:message code="post.editar.titulo"/></label>
       <form:input type="text" path="titulo" />
     </div>
     <div>
     <form:errors path="titulo"/>
     </div>
     <div>
      <!--    <label>Contenido</label>-->
      <label><spring:message code="post.editar.contenido"/></label>
        <form:textarea path="contenido" />
     </div>
     <div>
     <form:errors path="contenido"/>
     </div>
     <input type="submit" value="enviar"/>
   </form:form>

</body>
</html>