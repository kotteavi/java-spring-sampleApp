<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:choose>
	<c:when test="${hasOffer}">

		<a href="${pageContext.request.contextPath}/createoffer"> Edit or
			delete your current offer </a>

	</c:when>
	<c:otherwise>

		<a href="${pageContext.request.contextPath}/createoffer"> Add a
			new offer </a>

	</c:otherwise>
</c:choose>
&nbsp;
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="${pageContext.request.contextPath}/admin"> Admin </a>
</sec:authorize>
&nbsp;
<sec:authorize access="isAuthenticated()">
	<a href="<c:url value='/messages'/>"> Messages (<span
		id="numberMessages">0</span>)
	</a>
	<%-- <a href="<c:url value='/getmessages'/>"> Messages (<span
		id="numberMessages">0</span>)
	</a> --%>
</sec:authorize>



<script type="text/javascript">
	function updateMsgLink(data) {
		$("#numberMessages").text(data.number);
	}

	function onLoad() {
		updateToolbarPage();
		window.setInterval(updateToolbarPage, 5000);
	}

	function updateToolbarPage() {
		$.getJSON("<c:url value='/getmessages'/>", updateMsgLink);
	}

	$(document).ready(onLoad);
</script>
