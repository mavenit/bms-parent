<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@include file="../../taglib/taglib.jsp" %>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h3 class="page-header">Display <spring:message code="propsettings.header.label.propdet"/></h3>
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
						<div class="row">
							<div class="col-lg-12">
								<div class="panel panel-default">
									 <div class="panel-body">
									 	<table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
									 		 <thead>
									 			<tr>
			                                        <th><spring:message code="common.label.id"/></th>
			                                        <th><spring:message code="propsettings.label.propname"/></th>
			                                        <th><spring:message code="common.label.state"/></th>
			                                        <th><spring:message code="common.label.proptype"/></th>
			                                        <th><spring:message code="propsettings.label.units"/></th>                                      
			                                        <th><spring:message code="common.label.email"/></th>
			                                        <th><spring:message code="propsettings.label.action"/></th>
                                   				</tr>
									 		 </thead>
									 		 <c:choose>
									 		 	<c:when test="${empty results}">
									 		 		<strong> <spring:message code="common.no.results" /></strong>
									 		 	</c:when>
									 		    <c:otherwise>
									 		    	<c:forEach items="${results}" var="c" varStatus="status">
										 		 		<tbody>
												 		 	<tr class="odd gradeX">
												 		 		<td>${c.id}</td>
			                                        			<td>${c.name}</td>
			                                        			<td>${c.propState.name}</td>
			                                        			<td>${c.propType.name}</td>
			                                        			<td class="center">${c.totalUnits}</td>
			                                        			<td class="center">${c.email}</td>
			                                        			<td>
						                                            <ul class="navbar-top-links navbar-right">
						                                                <li class="dropdown">
						                                                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
						                                                        <i class="fa fa-align-justify fa-fw"></i>
						                                                    </a>
						                                                    <ul class="dropdown-menu dropdown-user">
						                                                        <li><a href="../${c.id}"><i class="fa fa-edit fa-fw"></i> Edit</a>
						                                                        </li>
						                                                        <li class="divider"></li>
						                                                        <li><a href="../view/${c.id}"><i class="fa fa-eye fa-fw"></i> View</a>
						                                                        </li>
						                                                        <li class="divider"></li>
						                                                        <li><a href="../delete/${c.id}"><i class="fa fa-times-circle fa-fw"></i> Delete</a>
						                                                        </li>
						                                                    </ul>
						                                                    <!-- /.dropdown-messages -->
						                                                </li>
						                                            </ul>
			                                       				</td>
												 		 	</tr>
										 		 		</tbody>
									 		 		 </c:forEach>
									 		 		<a:pagination />
									 		 	</c:otherwise>
									 		 </c:choose>
									 	</table>
									 </div>
								</div>
							</div>
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