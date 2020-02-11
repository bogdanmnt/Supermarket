<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<h1>Lista produselor existente in Supermarket</h1>


<p style="color: green">${adaugat}</p>
<p style="color: red">${neadaugat}</p>
<table  border="1">
	<tr><th>Id</th><th>Denumire</th><th>Pret</th><th>Stoc</th><th>Tip</th><th>Producator</th><th>Cumpara</th><tr>	

	<c:forEach items="${produse}" var="produs">
		<tr>
		    <td>${produs.id}</td>
			<td>${produs.denumire}</td>
			<td>${produs.pret}</td>
			<td>${produs.stoc}</td>
			<td>${produs.denumireTip}</td>
			<td>${produs.denumireProducator}</td>
			<td>
				<form action="cosCumparaturi" method="POST">
					<button type = "submit" name = "idProdusParam" value = "${produs.id}">ADAUGA IN COS</button>	
				</form>
			</td>
		</tr>
	</c:forEach>

</table>

<a href="cosCumparaturi">Vizualizeaza continutul cosului de cumparaturi</a>

<h2>Adauga un produs:</h2>

<form action="produse" method="POST">  <!-- by default e GET -->
<!--  folosim POST pentru ca facem modificare pe server: adaugam un nou produs -->
  
  Denumirea:<br>
  <input type="text" name="denumire" value=""><br>
  Pret:<br>
  <input type="text" name="pret" value=""><br>
  Stoc:<br>
  <input type="text" name="stoc" value=""><br><br>
  Alege tipul:
  <select name="tipId">
	  <c:forEach items="${tipuri}" var="tip">
	  	<option value="${tip.id}">${tip.denumire}</option>
	  </c:forEach>
  </select><br/>
  Alege producatorul:
  <select name="producatorId">
	  <c:forEach items="${producatori}" var="producator">
	  	<option value="${producator.id}">${producator.denumire}</option>
	  </c:forEach>
  </select><br/><br>
  <!--
  <select name="cars">
  <option value="volvo">Volvo</option>
  <option value="saab">Saab</option>
  <option value="opel">Opel</option>
  <option value="audi">Audi</option>
  </select> 
  --> 
  <input type="submit" value="Adauga produsul in baza de date">
  <!-- Cand userul apasa pe butonul de submit, browserul trimite un HTTP request catre server
  cu toti parametrii pe care i-a completat userul in formular -->
</form> 


</body>
</html>