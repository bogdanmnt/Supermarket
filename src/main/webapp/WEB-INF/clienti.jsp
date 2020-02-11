<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Lista clientilor care au cumparat vreodata de la "Ieftinel":</h1>

<table  border="1">
	<tr><th>Id</th><th>Nume</th><th>Prenume</th><tr>	

	<c:forEach items="${clienti}" var="client">
		<tr>
		    <td>${client.id}</td>
			<td>${client.nume}</td>
			<td>${client.prenume}</td>
		</tr>
	</c:forEach>

</table>

<h2>Adauga un client:</h2>

<form action="clienti" method="POST">  <!-- by default e GET -->
<!--  folosim POST pentru ca facem modificare pe server: adaugam un nou produs -->
  
  Nume:<br>
  <input type="text" name="nume" value=""><br/>
  Prenume:<br>
  <input type="text" name="prenume" value=""><br/>
  <input type="submit" value="Adauga clientul in baza de date">
  <!-- Cand userul apasa pe butonul de submit, browserul trimite un HTTP request catre server
  cu toti parametrii pe care i-a completat userul in formular -->
</form> 


</body>
</html>