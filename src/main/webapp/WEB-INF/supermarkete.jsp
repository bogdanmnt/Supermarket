<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Lista locatiilor Supermarketelor "Ieftinel"</h1>

<table  border="1">
	<tr><th>Id</th><th>Locatie</th><tr>	

	<c:forEach items="${supermarkete}" var="supermarket">
		<tr>
		    <td>${supermarket.id}</td>
			<td>${supermarket.locatie}</td>
			
		</tr>
	</c:forEach>

</table>

<h2>Adauga unde vrei sa mai construim un "Ieftinel":</h2>

<form action="supermarkete" method="POST">  <!-- by default e GET -->
<!--  folosim POST pentru ca facem modificare pe server: adaugam un nou produs -->
  
  Locatie:<br>
  <input type="text" name="locatie" value=""><br/>
 
  <input type="submit" value="Adauga supermarketul in baza de date">
  <!-- Cand userul apasa pe butonul de submit, browserul trimite un HTTP request catre server
  cu toti parametrii pe care i-a completat userul in formular -->
</form> 


</body>
</html>
