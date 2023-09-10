<%-- 
    Document   : index
    Created on : Jul 7, 2023, 1:08:19 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<section class="container">
    <h1 class="text-center text-info mt-1">Account List</h1>

    <table class="table table-hover">
        <thead>
            <tr>
                <th></th>
                <th>Id</th>
                <th>Username</th>
                <th>Role</th>
                <th>Is Confirmed</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${accounts}" var="account">
                <tr>
                    <td>
                        <img src="${account.imageUrl}" alt="${account.username}" width="120" />
                    </td>
                    <td>${account.userId}</td>
                    <td>${account.username}</td>
                    <td>${account.userRole}</td>
					<td>${account.isConfirmed ? 'Yes' : 'No'}</td>
					<td>
						<form method="post" action="/ecommerce/confirm">
							<input type="hidden" name="username" value="${account.username}">
							<button type="submit" class="btn btn-danger btn-sm">Confirm</button>
						</form>
					</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>

