<%--
  Created by IntelliJ IDEA.
  User: Ivan
  Date: 09.01.2018
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Save Customer</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/add-customer-style.css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship Manager</h2>
    </div>
</div>

<div id="container">
    <h3>Save Customer</h3>
    <form:form action="saveCustomer" modelAttribute="customer" method="post">
        <%-- need to assotiate the data of "customer" with given customer id (when we update the customer,
        we need to work with given customer and do not create the new one). As we HAVEN'T FORM INPUT for id,
        every time we push "Save" button we create new id and therefore new customer. To bind given customer
        with it's id, this can be done with "hidden" --%>
        <form:hidden path="id"/>

        <table>
            <tbody>
            <tr>
                <td><label>First name:</label></td>
                <td><form:input path="firstName"/></td>
            </tr>
            <tr>
                <td><label>Last name:</label></td>
                <td><form:input path="lastName"/></td>
            </tr>
            <tr>
                <td><label>Email:</label></td>
                <td><form:input path="email"/></td>
            </tr>
            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save"/></td>
            </tr>
            </tbody>
        </table>
    </form:form>
    <div style="clear: both"></div>
    <p>
    <div id="mynewstyle">
        <h3><a href="${pageContext.request.contextPath}/customer/list">Back to List</a></h3>
    </div>
    </p>
</div>
</body>
</html>
