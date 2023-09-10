<%-- 
    Document   : index
    Created on : Jul 7, 2023, 1:08:19 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<section class="container">
    <h1 class="text-center text-info mt-1">Product List</h1>

    <table class="table table-hover">
        <thead>
            <tr>
                <th></th>
                <th>Id</th>
                <th>Name</th>
                <th>Price</th>
                <th>Seller</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${products}" var="p">
                <tr>
                    <td>
                        <img src="${p.imageUrl}" alt="${p.name}" width="120" />
                    </td>
                    <td>${p.productId}</td>
                    <td>${p.name}</td>
                    <td>${p.price}</td>
                    <td>${p.userId.username}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>
