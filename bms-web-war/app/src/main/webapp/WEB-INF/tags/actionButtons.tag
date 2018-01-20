<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="buttons">
	<c:if test="${update=='update_record'}">
		<button type="submit" name="bsubmit" value="save" class="btn btn-default">
			<spring:message code="button.update" />
		</button>
	</c:if>
	<c:if test="${update!='update_record'}">
		<button type="submit" name="bsubmit" value="savenew" class="btn btn-default">
			<spring:message code="button.savenew" />
		</button>
	</c:if>
	<button type="reset" class="btn btn-default"> Reset </button>
	<button type="button" class="btn btn-default"> Cancel </button>
</div>