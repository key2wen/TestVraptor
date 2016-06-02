
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<h1>this is error page haha(有错)</h1>


<p>
    error :<br/>
    ${"javax.servlet.error.exception"}<br/>
    ${"javax.servlet.error.message"}<br/>
    ${myerror}
</p>
</body>
</html>
