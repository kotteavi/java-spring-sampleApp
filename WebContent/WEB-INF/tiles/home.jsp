<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div>
	<table>
		<tr>
			<th>Name</th>
			<th>Email</th>
			<th>Text</th>
		</tr>
		<c:forEach var="offer" items="${availableOffers}">
			<tr>
				<td><c:out value="${offer.user.name}"></c:out></td>
				<td><a href="<c:url value='/message?uid=${offer.username}'/>">
						contact </a></td>
				<td><c:out value="${offer.text}"></c:out></td>
			</tr>
		</c:forEach>
	</table>
</div>



