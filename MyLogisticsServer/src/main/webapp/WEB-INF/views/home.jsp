<%@ page session="false" %>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<!DOCTYPE html>
<html>
	<head>
		<title>Spring Mobile | Lite Device Delegating View Resolver</title>
	</head>
	<body>
		<header>
			<h1>Home (Normal Site)</h1>
		</header>
		<p>View the <a href="<c:url value="/about" />">About</a> page.</p>
		<%@include file="includes/menu.jsp" %>
		<%@include file="includes/content.jsp" %>
		<%@include file="includes/footer.jsp" %>
	</body>
</html>
