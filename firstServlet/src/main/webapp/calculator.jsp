<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Form</title>
</head>
<body>

    <%
        if (request.getAttribute("errorMessage") != null) {
             out.print(request.getAttribute("errorMessage"));
        }
    %>
    <br>
    <jsp:include page="form.jsp" />
    Result:
    <%
        if (request.getAttribute("result") != null) {
            out.print(request.getAttribute("result"));
        }
    %>
</body>
</html>