<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Form</title>
</head>
<body>
    <jsp:include page="form.jsp" />
    Result:
    <%
        if (request.getParameterNames().hasMoreElements()) {
            out.print(request.getAttribute("result"));
        }
    %>
</body>
</html>