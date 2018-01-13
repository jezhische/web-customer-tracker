<%--
  Created by IntelliJ IDEA.
  User: Ivan
  Date: 03.01.2018
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--<!DOCTYPE html>--%>
<html>
<head>
    <title>List Customers</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div id="wrapper"> <!-- APPLYING CSS STYLES -->
    <div id="header"> <!-- APPLYING CSS STYLES - try <div id="header h2"> - see style.css  -->
        <h2>CRM - Customer Relationship Manager</h2>
    </div>
</div>
<div id="container"> <!-- APPLYING CSS STYLES -->
    <div id="content"> <!-- APPLYING CSS STYLES: #container #content {margin-top: 20px;} -->
        <%-- put new button: Add Customer --%>
        <input type="button" value="Add Customer"
               onclick="window.location.href='showFormForAdd'; return false;"
               class="add-button"
        />     <!-- APPLYING CSS STYLES: add-button is the class .add-button in style.css -->
        <%-- add a search box --%>
        <form:form action="search" method="post">
            Search customer: <input type="text" name="theSearchName"/>
            <input type="submit" value="Search" class="add-button"/>
        </form:form>

        <!-- add html table here -->
        <table>
            <tr>
                <th>Id</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
            <%-- loop over and print customers --%>
            <c:forEach var="tempCustomer" items="${customers}">
                <%-- NB: создаем переменные, которые обеспечат линк для "update" и "delete" для каждого tempCustomer
                во время итерации --%>
                <%-- construct an "update" link with customer id - create a new var witn appropriate url value, and
                add a value of id param --%>
                <c:url var="updateLink" value="/customer/showFormForUpdate">
                    <c:param name="customerId" value="${tempCustomer.id}"/>
                </c:url>
                <c:url var="deleteLink" value="/customer/delete">
                    <c:param name="customerId" value="${tempCustomer.id}"/>
                </c:url>
                <tr>
                    <td>${tempCustomer.id}</td>
                    <td>${tempCustomer.firstName}</td>
                    <td>${tempCustomer.lastName}</td>
                    <td>${tempCustomer.email}</td>
                    <%-- создаем ссылку, у которой URL = /customer/showFormForUpdate для Update и соотв. для Delete;
                     NB: для delete работает javascript--%>
                    <td><a href="${updateLink}">Update</a> | <a href="${deleteLink}"
                    onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false;">
                        Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="mynewstyle">
        <h3>
            <a href="${pageContext.request.contextPath}/">home</a>
        </h3>
    </div>
</div>
</body>
</html>
