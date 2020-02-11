<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Lista facturilor existente</h1>

<table  border="1">
	<tr><th>Cod</th><th>Data Facturare</th><th>Pret total</th><th>Supermarket</th><tr>	

	<c:forEach items="${facturi}" var="factura">
		<tr>
		    <td>${factura.cod}</td>
			<td>${factura.dataFacturare}</td>
			<td>${factura.pretTotal}</td>
			<td>${factura.denumireSupermarket}</td>
		</tr>
	</c:forEach>

</table>

<h2>Adauga o factura:</h2>

<form action="facturi" method="POST">  <!-- by default e GET -->
<!--  folosim POST pentru ca facem modificare pe server: adaugam un nou produs -->
  
  Cod:<br>
  <input type="text" name="cod" value="">
  <br>
  Pret Total:<br>
  <input type="text" name="pretTotal" value="">
  <br>
  Alege Supermarketul:
  
  <select name="supermarketId">
	  <c:forEach items="${supermarkete}" var="supermarket">
	  	<option value="${supermarket.id}">${supermarket.locatie}</option>
	  </c:forEach>
  </select><br/>
  <!--
  <select name="cars">
  <option value="volvo">Volvo</option>
  <option value="saab">Saab</option>
  <option value="opel">Opel</option>
  <option value="audi">Audi</option>
  </select> 
  --> 
  <input type="submit" value="Adauga factura in baza de date">
  <!-- Cand userul apasa pe butonul de submit, browserul trimite un HTTP request catre server
  cu toti parametrii pe care i-a completat userul in formular -->
</form> 
</body>
</html>