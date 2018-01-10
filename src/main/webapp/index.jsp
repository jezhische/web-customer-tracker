<%-- todo: <% response.sendRedirect("customer/list"); %>--%>
<html>
<head>
    <title>CRM Home</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div id="header">
    <h2>Customer Relationship Manager home page</h2>
    <br><hr><br>
</div>
<div id="mynewstyle">
    <h3><a href="${pageContext.request.contextPath}/customer/list">Customer List</a></h3>
</div>
</body>
</html>
