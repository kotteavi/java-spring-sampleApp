
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">

function onDeleteClick(event) {
	
	var doDelete = confirm("Are you sure you want to delete this offer?");
	
	if(doDelete == false) {
		event.preventDefault();
	}
}

function onReady() {
	$("#delete").click(onDeleteClick);
}

$(document).ready(onReady);

</script>

<sf:form method="post"
	action="${pageContext.request.contextPath}/docreate"
	commandName="offer">

	<sf:input type="hidden" name="id" path="id" />

	<table class="formTable">
		<tr>
			<td class="label">Your offer:</td>
			<td><sf:textarea path="text" class="control" name="text"></sf:textarea>
				<br> <sf:errors cssClass="error" path="text">
				</sf:errors></td>
		</tr>
		<tr>
			<td class="label"></td>
			<td><input class="control" type="submit" value="save advert" /></td>
		</tr>
		<c:if test="${offer.id != 0}">
			<tr>
				<td class="label"></td>
				<td><input class="delete control" type="submit" name="delete"
					id="delete" value="delete this offer" /></td>
			</tr>
		</c:if>
	</table>
</sf:form>
