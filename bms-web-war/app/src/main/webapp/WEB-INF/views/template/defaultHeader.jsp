<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 <!-- Navigation -->
            <div class="navbar-header">
               <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
               <span class="sr-only">Toggle navigation</span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               </button>
               <a class="navbar-brand" href="index.html">Building Management System</a> &nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <!-- /.navbar-header -->
            <!-- /.notification messages -->
            <ul class="nav navbar-top-links navbar-right">
               <!-- Correspondance Notifications -->
               <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                  <i class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
                  </a>
                  <ul class="dropdown-menu dropdown-messages">
                     <li>
                        <a href="#">
                           <div>
                              <strong>John Smith</strong>
                              <span class="pull-right text-muted">
                              <em>Yesterday</em>
                              </span>
                           </div>
                           <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                        </a>
                     </li>
                     <li class="divider"></li>
                     <li>
                        <a class="text-center" href="#">
                        <strong>Read All Messages</strong>
                        <i class="fa fa-angle-right"></i>
                        </a>
                     </li>
                  </ul>
                  <!-- /.dropdown-messages -->
               </li>
               <!-- /.dropdown -->
               <!-- SOP Tasks Notifications -->
               <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                  <i class="fa fa-tasks fa-fw"></i> <i class="fa fa-caret-down"></i>
                  </a>
                  <ul class="dropdown-menu dropdown-tasks">
                     <li>
                        <a href="#">
                           <div>
                              <p>
                                 <strong>SOP Tasks</strong>
                                 <span class="pull-right text-muted">40% Complete</span>
                              </p>
                              <div class="progress progress-striped active">
                                 <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                    <span class="sr-only">40% Complete (success)</span>
                                 </div>
                              </div>
                           </div>
                        </a>
                     </li>
                     <li class="divider"></li>
                     <li>
                        <a class="text-center" href="#">
                        <strong>See All Tasks</strong>
                        <i class="fa fa-angle-right"></i>
                        </a>
                     </li>
                  </ul>
                  <!-- /.dropdown-tasks -->
               </li>
               <!-- /.dropdown -->
               <!-- Task  -->
               <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                  <i class="fa fa-cog fa-fw"></i> <i class="fa fa-caret-down"></i>
                  </a>
                  <ul class="dropdown-menu dropdown-alerts">
                     <li>
                        <a href="#">
                           <div>
                              <i class="fa"></i> Tasks
                              <span class="pull-right text-muted small">4 minutes ago</span>
                           </div>
                        </a>
                     </li>
                     <li class="divider"></li>
                     <li>
                        <a class="text-center" href="#">
                        <strong>See All Alerts</strong>
                        <i class="fa fa-angle-right"></i>
                        </a>
                     </li>
                  </ul>
                  <!-- /.dropdown-alerts -->
               </li>
               <!-- /.dropdown -->
               <!-- Notifications  -->
               <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                  <i class="fa fa-warning fa-fw"></i> <i class="fa fa-caret-down"></i>
                  </a>
                  <ul class="dropdown-menu dropdown-alerts">
                     <li>
                        <a href="#">
                           <div>
                              <i class="fa"></i> Notifications
                              <span class="pull-right text-muted small">4 minutes ago</span>
                           </div>
                        </a>
                     </li>
                     <li class="divider"></li>
                     <li>
                        <a class="text-center" href="#">
                        <strong>See All Alerts</strong>
                        <i class="fa fa-angle-right"></i>
                        </a>
                     </li>
                  </ul>
                  <!-- /.dropdown-alerts -->
               </li>
               <!-- /.dropdown -->
               <!-- Reminders  -->
               <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                  <i class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
                  </a>
                  <ul class="dropdown-menu dropdown-alerts">
                     <li>
                        <a href="#">
                           <div>
                              <i class="fa"></i> Reminders
                              <span class="pull-right text-muted small">4 minutes ago</span>
                           </div>
                        </a>
                     </li>
                     <li class="divider"></li>
                     <li>
                        <a class="text-center" href="#">
                        <strong>See All Alerts</strong>
                        <i class="fa fa-angle-right"></i>
                        </a>
                     </li>
                  </ul>
                  <!-- /.dropdown-alerts -->
               </li>
               <!-- /.dropdown -->
               <!-- Todo list  -->
               <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                  <i class="fa fa-calendar fa-fw"></i> <i class="fa fa-caret-down"></i>
                  </a>
                  <ul class="dropdown-menu dropdown-alerts">
                     <li>
                        <a href="#">
                           <div>
                              <i class="fa"></i> Todo list
                              <span class="pull-right text-muted small">4 minutes ago</span>
                           </div>
                        </a>
                     </li>
                     <li class="divider"></li>
                     <li>
                        <a class="text-center" href="#">
                        <strong>See All Alerts</strong>
                        <i class="fa fa-angle-right"></i>
                        </a>
                     </li>
                  </ul>
                  <!-- /.dropdown-alerts -->
               </li>
               <!-- /.dropdown -->
               <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                  <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                  </a>
                  <ul class="dropdown-menu dropdown-user">
                  	 <li>
                    	 <sec:authorize access="isAuthenticated()">
       						<i class="fa fa-user fa-fw">Welcome, <sec:authentication property="principal.username"/>  </i>
		   				</sec:authorize>
                     </li>
                     <li>
                     	<a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                     </li>
                     <li>
                     	<a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                     </li>
                     <li class="divider"></li>
                     <li>
                     	<sec:authorize access="isAuthenticated()">
        					<a href="${pageContext.request.contextPath}/logout-process"><i class="fa fa-sign-out fa-fw"><spring:message code="menu.logout"/> </i></a>
						</sec:authorize>
						<sec:authorize access="isAnonymous()">
			        		<a href="${pageContext.request.contextPath}/login"><i class="fa fa-sign-out fa-fw"><spring:message code="menu.login"/></i></a>
						</sec:authorize>
                     
                     	<%-- <a href="${pageContext.request.contextPath}/logout-process"><i class="fa fa-sign-out fa-fw"></i> Logout</a> --%>
                     </li>
                  </ul>
                  <!-- /.dropdown-user -->
               </li>
               <!-- /.dropdown -->
            </ul>