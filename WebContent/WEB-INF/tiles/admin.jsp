<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

Secret authoritized users only

<table>
	<tr>
		<th>Username</th>
		<th>email</th>
		<th>role</th>
		<th>enabled</th>
	</tr>
	<c:forEach var="user" items="${users }">
		<tr>
			<td><c:out value="${user.username }"></c:out></td>
			<td><c:out value="${user.email }"></c:out></td>
			<td><c:out value="${user.authority }"></c:out></td>
			<td><c:out value="${user.enabled }"></c:out></td>
		</tr>
	</c:forEach>
</table>
