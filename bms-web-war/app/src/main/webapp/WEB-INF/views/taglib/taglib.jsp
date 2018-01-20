<%-- @Author : Sudhakar --%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="crt" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"  %>
<%@taglib prefix="display_el" uri="http://displaytag.sf.net/el"  %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/service_page.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom_style.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/portal.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.3.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/portal.js"></script> --%>
<title>BMS Portal</title>

<script language="JavaScript" type="text/javascript">
history.forward(); 
/* document.onmousedown = disable_right_click;
function disable_right_click(){
        if (event.button == 2) {
        	alert("Copyright (c) 2017 reserved by mavenitsolutions Technical Services.");
        }
}
  */

</script>
<c:set var="select_value"><spring:message code="common.select.value"/></c:set>
<c:set var="value_jmb"><spring:message code="common.value.jmb"/></c:set>
<c:set var="value_mc"><spring:message code="common.value.mc"/></c:set>
<%
if( !response.isCommitted() )
{
      response.setDateHeader("Expires", 0); //prevents caching at the proxy server
      response.setHeader("Cache-Control", "private, no-store, no-cache,must-revalidate, max-stale=0"); //HTTP 1.1
      response.addHeader("Cache-Control", "post-check=0, pre-check=0"); //IE Specific
      response.setHeader("Pragma","no-cache"); //HTTP 1.0
}
%>