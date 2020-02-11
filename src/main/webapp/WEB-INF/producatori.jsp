<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Lista producatorilor furnizori ai Supermarketului</h1>

<table  border="1">
	<tr><th>Id</th><th>Denumire</th><th>Adresa</th><tr>	

	<c:forEach items="${producatori}" var="producator">
		<tr>
		    <td>${producator.id}</td>
			<td>${producator.denumire}</td>
			<td>${producator.adresa}</td>
		</tr>
	</c:forEach>

</table>

<h2>Adauga un producator:</h2>

<form action="producatori" method="POST">  <!-- by default e GET -->
<!--  folosim POST pentru ca facem modificare pe server: adaugam un nou produs -->
  
  Denumirea:<br>
  <input type="text" name="denumire" value=""><br/>
  Adresa:<br>
  <input type="text" name="adresa" value=""><br/>
  <input type="submit" value="Adauga producatorul in baza de date">
  <!-- Cand userul apasa pe butonul de submit, browserul trimite un HTTP request catre server
  cu toti parametrii pe care i-a completat userul in formular -->
</form> 


</body>
</html>