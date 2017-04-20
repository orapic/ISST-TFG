<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<% BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
</head>
<body>

<p>TFAGs management system</p>
	<c:if test="${not empty user}">
		<p> Hola Señor <c:out value="${user}" /> </p>
		<c:if test="${empty tfgs }">
		<p>Ha sido reconocido como estudiante</p>
			<c:if test="${empty tfg }">
			<p>Formulario para solicitar TFG</p>
				<form action="/nuevoTFG" method="post" acceptcharset="utf-8">
					<input type="text" name="titulo" id="titulo" maxLength="255"
						size="20" required placeholder="Título" />
					<input type="text" name="tutor" id="tutor"
						maxLength="255" required size="20" placeholder="Tutor" />
					<input type="text" name="secretario" id="secretario"
						maxLength="255" required size="20" placeholder="Secretario" />
					<input type="submit" value="Enviar" />
				</form>
			</c:if>
			<c:if test="${not empty tfg }">
				<p>Los datos de tu TFG son: </p>
				<p>Autor: <c:out value="${tfg.autor }"></c:out></p>
				<p>Titulo: <c:out value="${tfg.titulo }"></c:out></p>
				<p>Tutor: <c:out value="${tfg.tutor }"></c:out></p>
				<p>Secretario: <c:out value="${tfg.secretario }"></c:out></p>
				<p>Estado: <c:out value="${tfg.estado }"></c:out></p>
			</c:if>
			
		</c:if>
		<c:if test="${not empty tfgs }">
		<p>Ha sido reconocido como profesor</p>
		<table>
			<tr>
				<td>Autor</td>
				<td>Titulo</td>
				<td>Memoria</td>
				<td>Secretario</td>
				<td>Estado</td>
			</tr>
			
			<c:forEach items="${tfgs}" var="tfgi">
				<tr>
					<td><c:out value="${tfgi.autor}" /></td>
					<td><c:out value="${tfgi.titulo}" /></td>
					<td><c:out value="${tfgi.memoria}" /></td>
					<td><c:out value="${tfgi.secretario}" /></td>
					<td><c:out value="${tfgi.estado}" /></td>
				</tr>
			</c:forEach>
			</table>

		</c:if>
	</c:if>
<p>You can press the next link to <c:out value="${urlLinktext}"/>, fag
<a href="<c:url value="${url}"/>"><c:out value="${urlLinktext}"/></a></p>
<c:if test="${empty user }"><p>Eres un pleb </p></c:if>


<c:if test="${not null user and not null tfg and tfg.estado == 2}">
	Document upload form. <c:out value="${tfg.memoria}" />
	<form action="<%=blobstoreService.createUploadUrl("/upload")%>"
		method="post" enctype="multipart/form-data">
		<input id="autor" name="autor" type="hidden" value="${tfg.autor}" />
		<input type="file" name="file" />
              <input type="submit" value="Upload document" />
	</form>
</c:if>



</body>
</html>