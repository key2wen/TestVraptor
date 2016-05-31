
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<h1>hello show(列表)</h1>

<h2>products:</h2>
<ul>

        <li> ${product.name} - ${product.description}- ${product.price} </li>

</ul>

</body>
</html>
