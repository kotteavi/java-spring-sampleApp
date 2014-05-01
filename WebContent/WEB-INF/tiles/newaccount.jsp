<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h2>Create New Account</h2>

<sf:form id="details" method="post"
	action="${pageContext.request.contextPath}/accountcreated"
	commandName="user">
	<table class="formTable">
		<tr>
			<td class="label">Name:</td>
			<td><sf:input path="name" class="control" type="text"
					name="name" /> <br> <sf:errors cssClass="error"
					path="name">
				</sf:errors></td>
		</tr>
		<tr>
			<td class="label">Username:</td>
			<td><sf:input path="username" class="control" type="text"
					name="username" /> <br> <sf:errors cssClass="error"
					path="username">
				</sf:errors></td>
		</tr>
		<tr>
			<td class="label">Email:</td>
			<td><sf:input path="email" class="control" type="text"
					name="email" /> <br> <sf:errors cssClass="error" path="email">
				</sf:errors></td>
		</tr>
		<tr>
			<td class="label">Password:</td>
			<td><sf:input path="password" id="password" class="control"
					type="password" name="password" /> <br> <sf:errors
					cssClass="error" path="password">
				</sf:errors></td>
		</tr>
		<tr>
			<td class="label">Confirm password:</td>
			<td><input class="control" id="confirmpass" type="password"
				name="confirmPass" />
				<div id="matchpass"></div></td>
		</tr>

		<tr>
			<td class="label"></td>
			<td><input class="control" type="submit" value="create account" /></td>
		</tr>
	</table>
</sf:form>
