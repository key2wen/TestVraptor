
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<title>提交表单</title>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<h1>hello form</h1>

<form action="<c:url value='/products/add'/>">
    名称:             <input type="text" name="product.name" /><br/>
    Description:<input type="text" name="product.description" /><br/>
    Price:            <input type="text" name="product.price" /><br/>
    <input type="submit" value="Save" />
</form>

<ul>

    <c:forEach var="error" items="${errors}">
        ${error.category} : ${error.message}<br />
    </c:forEach>

    <c:if test="${errors} != null">
        <c:forEach var="error" items="${errors}">
            ${error.category} : ${error.message}<br />
        </c:forEach>
    </c:if>

</ul>

</body>
</html>
