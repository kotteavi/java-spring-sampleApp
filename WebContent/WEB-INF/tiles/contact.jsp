<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h2> Send message </h2>

<sf:form method="post" commandName="message">

	<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
	<input type="hidden" name="_eventId" value="send" />

	<table class="formTable">
		<tr>
			<td class="label">Name:</td>
			<td><sf:input path="name" class="control" type="text" value="${fromName}"/> <br>
				<sf:errors cssClass="error" path="name">
				</sf:errors></td>
		</tr>
		<tr>
			<td class="label">Email:</td>
			<td><sf:input path="email" class="control" type="text" value="${fromEmail}"/> <br>
				<sf:errors cssClass="error" path="email">
				</sf:errors></td>
		</tr>

		<tr>
			<td class="label">Subject:</td>
			<td><sf:input path="subject" class="control" type="text" /> <br>
				<sf:errors cssClass="error" path="subject">
				</sf:errors></td>
		</tr>
		<tr>
			<td class="label">Content:</td>
			<td><sf:textarea path="content" class="control" type="text" />
				<br> <sf:errors cssClass="error" path="content">
				</sf:errors></td>
		</tr>
		<tr>
			<td class="label"></td>
			<td><input class="control" type="submit" value="Send message" /></td>
		</tr>
	</table>
</sf:form>
