<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@include file="../../taglib/taglib.jsp" %>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h3 class="page-header">Create <spring:message code="propsettings.header.label.propdet"/> </h3>
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

				<div class="tab-content">
					<div class="tab-pane fade in active" id="home">
						<div class="col-lg-6">
						<form:form modelAttribute="detailsMasterInfo"  method="POST" role="form" enctype="multipart/form-data" >
							<%-- 
							action="/bms-portal/settings/create?${_csrf.parameterName}=${_csrf.token}"
							<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
							<input type="hidden" name="_csrf" value="${_csrf.token}"> --%>
							<sec:csrfInput />
							<!-- <form role="form"> action="/bms-portal/settings/create?${_csrf.parameterName}=${_csrf.token}"-->
								<div class="form-group">
									<label><spring:message code="propsettings.label.propname"/></label> 
									<form:input path="model.name" maxlength="32" tabindex="1" class="form-control" placeholder="Enter Property Name"/>
									<form:errors path="model.name" />
								</div>
								<div class="form-group">
									<label><spring:message code="propsettings.label.units"/></label>
									<form:input path="model.totalUnits" maxlength="32" tabindex="2" class="form-control" placeholder="Enter no of Units"/>
									<form:errors path="model.totalUnits" />
									 
								</div>
								<div class="form-group">
									<label><spring:message code="common.label.proptype"/></label> 
									<form:select path="model.propTypeId" tabindex="3" class="form-control">
										<form:option label="${select_value}" value=""></form:option>
										<form:options items="${propTypeList}" itemValue="id" itemLabel="name" /> 
									</form:select>
								</div>
								<div class="form-group">
									<label><spring:message code="common.label.email"/></label> 
									<form:input path="model.email" maxlength="32" tabindex="4" class="form-control" placeholder="Enter Email Address"/>
									<form:errors path="model.email" />
								</div>
								<div class="form-group">
									<label><spring:message code="common.label.state"/></label> 
									<form:select path="model.stateId" tabindex="5" class="form-control">
										<form:option label="${select_value}" value=""></form:option>
										<form:options items="${stateList}" itemValue="id" itemLabel="name" /> 
									</form:select>
								</div>
								<div class="form-group">
									<label>Type</label>
									<label class="checkbox-inline"> 
										<form:radiobutton path = "model.jmbMc" value = "${value_jmb}" label = "${value_jmb}" tabindex="6"/>
									</label> 
									<label class="checkbox-inline"> 
										<form:radiobutton path = "model.jmbMc" value ="${value_mc}" label = "${value_mc}" />
									</label>
								</div>
								<div class="form-group">
									<label><form:label path="model.file"> <spring:message code="common.label.fileupload"/></form:label>  </label>
									 <input type="file" name="file" tabindex="7" />
								</div>
								<div class="form-group">
									<label><spring:message code="propsettings.label.description"/></label>
									<form:textarea path="model.description" maxlength="32" tabindex="8" rows="3" class="form-control" placeholder="Enter description"/>
									<form:errors path="model.description" />
								</div>
								<a:actionButtons />
							</form:form>
						</div>
					</div>

				</div>
			</div>
			<!-- /.panel-body -->

			<!-- /.panel -->
		</div>

	</div>
</div>
<!-- /#page-wrapper -->