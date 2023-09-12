<%-- 
    Document   : header
    Created on : Jul 21, 2023, 1:12:19 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/" var="action" />
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">E-commerce Website</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${action}">Home</a>
                </li>
                <li class="nav-item">
					<a class="nav-link" href="<c:url value="/accounts" />">Account Management</a>
                </li>
				<c:choose>
					<c:when test="${pageContext.request.userPrincipal.name != null}">
						<li class="nav-item">
							<a class="nav-link" href="<c:url value="/accounts" />">${pageContext.request.userPrincipal.name}</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="nav-item">
							<a class="nav-link" href="<c:url value="/login" />">Login</a>
						</li>
					</c:otherwise>
				</c:choose>
            </ul>
        </div>
    </div>
</nav>