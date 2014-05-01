<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript">
	$(document).ready(function() {
		document.f.j_username.focus();
	});
</script>

<h3>Login with Username and Password</h3>
<form name='f'
	action='${pageContext.request.contextPath}/j_spring_security_check'
	method='POST'>
	<table>
		<tr>
			<td>User:</td>
			<td><input type='text' name='j_username' value=''></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type='password' name='j_password' /></td>
		</tr>
		<tr>
			<td>Remember me:</td>
			<td><input type="checkbox" name='_spring_security_remember_me' /></td>
		</tr>
		<tr>
			<td colspan='2'><input name="submit" type="submit" value="Login" /></td>
		</tr>
	</table>
</form>
<c:if test="${param.error == true}">
	<p class="errorMsg">Incorrect user name or password</p>
</c:if>

<p>
	<a href="<c:url value="/newaccount"/>">Create new account</a>
</p>
