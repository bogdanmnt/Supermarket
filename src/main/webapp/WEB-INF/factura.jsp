<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<h1>Factura ${codFactura}</h1>


<table  border="1">
	<tr><th>produsId</th><th>denumire</th><th>pretUnitar</th><th>denumireProducator</th><th>cantitate</th><th>pretUnitar*Cantitate</th><tr>	

	<c:forEach items="${produseVandute}" var="produs">
		<tr>
		    <td>${produs.id}</td>
			<td>${produs.denumire}</td>
			<td>${produs.pretUnitar}</td>
			<td>${produs.denumireProducator}</td>
			<td>${produs.cantitate}</td>
			<td>${produs.pretulUnitarOriCantitate}</td>
		</tr>
	</c:forEach>

</table>


<p>Pretul total al facturii este ${pretTotal}</p>
</body>
</html>