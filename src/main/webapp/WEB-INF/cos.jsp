<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<h1>Lista produselor din cosul de comparaturi</h1>

<p style="color: red">${error}</p>

<table  border="1">
	<tr><th>Id</th><th>Denumire</th><th>Pret</th><th>Tip</th><th>Producator</th>
	<c:forEach items="${cosJsp}" var="produs">
		<tr>
		    <td>${produs}</td>
			<!-- <td></td>
			<td></td>
			<td></td>
			<td></td> -->
		</tr>
	</c:forEach>

</table>

<h2>Du-te la casa de marcat:</h2>
		<form action="facturi" method="POST">
			<button type = "submit">Finalizeaza cumparaturile</button>	
		</form>

<!-- Aici trebuie creata o factura care sa contina toate produsele cumparate (vanzarile) 
	In servletulFActura in doPost cream facutura o factura goala. 
	
	trebuie adaugate in baza de date toate vanzarile pentru fiecare produs.	
	luam idurile produselor din cosul de cumparaturi care se afla pe sesiune.
	Nota: e un pic de lucru la cantitate. 
	La fiecare produs trebuie verificat stocul.
	Cand se creaza vanzarile se adauga codul facuturii.
		
	Se sterg toate produsele din cosul de cumparaturi. list.removeAll()
	
	Pretul total al facturii:
	Trebuie obtinut pretul fiecarui produs din baza de date.
	
	Ultimul lucru: trebuie scazut stocul.
	
	returnam factura.jsp
-->

</body>
</html>