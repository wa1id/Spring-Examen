<%@ page language='java' contentType='text/html; charset=UTF-8'
    pageEncoding='UTF-8'%>
<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>

<link rel='stylesheet' href='/resources/css/bootstrap.min.css'>

<title>in-memory jokes</title>
</head>

<body>

<div>
<h1>in-memory jokes</h1>
<br/><br/>
<table>
	<tr>
		<th>Joke</th>
	</tr>
	<c:if test='${not empty jokes}'>
		<c:forEach var='j' items='${jokes}'>
			<tr><td>${j.joke}</td></tr>
		</c:forEach>
	</c:if>
</table>

<br/><br/>
</div>

</body>
</html>

