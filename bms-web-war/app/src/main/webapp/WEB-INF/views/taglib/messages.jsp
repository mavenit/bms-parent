<%@include file="taglib.jsp" %>

<c:if test="${alertType=='success'}">
	<div class="alert alert-success">
	    <strong>Success!</strong> ${alertMessage}
	  </div>
</c:if>
 
<c:if test="${alertType=='info'}">
	<div class="alert alert-info">
		<strong>Info!</strong> ${alertMessage}
	</div>
</c:if>

<c:if test="${alertType=='warning'}">
	<div class="alert alert-warning">
		<strong>Warning!</strong> ${alertMessage}
	</div>
</c:if>

<c:if test="${alertType=='error'}">
	<div class="alert alert-danger">
		<strong>Error!</strong> ${alertMessage}
	</div>
</c:if>
