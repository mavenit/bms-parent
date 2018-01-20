 <%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@include file="../../taglib/taglib.jsp" %>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h3 class="page-header">Display <spring:message code="propsettings.header.label.propdet"/> </h3>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	
	<div class="row">
		<div class="col-lg-12">
			<!-- /.panel-heading -->
			<div class="panel-body">
				<!-- Nav tabs -->
				<!-- Tab panes -->
				<div class="clear"></div>
				<span class="border-right-0>
				<div class="tab-content">
					<div class="tab-pane fade in active" id="home">
						<div class="col-lg-6">
						<form:form modelAttribute="detailsMasterInfo" method="POST" >
							<table class="table table-sm table-inverse">
								<tbody>
									<tr>
										<td><h4><spring:message code="propsettings.label.propname"/></h4> </td>
										<td><h5> <c:out value="${model.name}" /> </h5></td>
									</tr>
									<tr>
										<td><h4><spring:message code="propsettings.label.units"/> </h4> </td>
										<td><h5>  <c:out value="${model.totalUnits}" /> </h5></td>
									</tr>
									<tr>
										<td><h4><spring:message code="common.label.proptype"/> </h4></td>
										<td><h5><c:out value="${model.propType.name}" /></h5></td>
									</tr>
									<tr>
										<td><h4><spring:message code="common.label.email"/></h4> </td>
										<td><h5><c:out value="${model.email}" /> </h5></td>
									</tr>
									<tr>
										<td><h4><spring:message code="common.label.state"/> </h4></td>
										<td><h5><c:out value="${model.propState.name}" /></h5></td>
									</tr>
									<tr>
										<td><h4>Type </h4></td>
										<td><h5><c:out value="${model.jmbMc}" /> </h5></td>
									</tr>
									<tr>
										<td><h4><spring:message code="common.label.fileupload"/></h4> </td>
										<td></td>
									</tr>
									<tr>
										<td><h4><spring:message code="propsettings.label.description"/></h4> </td>
										<td><p><h5><c:out value="${model.description}" /></h5></p></td>
									</tr>
								</tbody>
							</table>
							</form:form>
						</div>
					</div>
				</div>
				</span>
			</div>
			<!-- /.panel-body -->

			<!-- /.panel -->
		</div>

	</div>
</div>
<!-- /#page-wrapper -->