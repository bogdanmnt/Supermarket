<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Lista raioanelor existente in Supermarket</h1>

<table  border="1">
	<tr><th>Id</th><th>Denumire</th><tr>	

	<c:forEach items="${raioane}" var="raion">
		<tr>
		    <td>${raion.id}</td>
			<td>${raion.denumire}</td>
			
		</tr>
	</c:forEach>

</table>

<h2>Adauga un raion:</h2>

<form action="raioane" method="POST">  <!-- by default e GET -->
<!--  folosim POST pentru ca facem modificare pe server: adaugam un nou produs -->
  
  Denumirea:<br>
  <input type="text" name="denumire" value=""><br/>
 
  <input type="submit" value="Adauga raionul in baza de date">
  <!-- Cand userul apasa pe butonul de submit, browserul trimite un HTTP request catre server
  cu toti parametrii pe care i-a completat userul in formular -->
</form> 


</body>
</html>