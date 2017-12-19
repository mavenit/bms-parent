<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@include file="../taglib/taglib.jsp" %>

<html>

<head>
	<meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="description" content="">
      <meta name="author" content="">

    <title>Maven IT Solutions - Building Management System - Login</title>

	<!-- Bootstrap Core CSS -->
      <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
      <!-- MetisMenu CSS -->
      <link href="${pageContext.request.contextPath}/resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
      <!-- Custom CSS -->
      <link href="${pageContext.request.contextPath}/resources/dist/css/sb-admin-2.css" rel="stylesheet">
      <!-- Morris Charts CSS -->
      <link href="${pageContext.request.contextPath}/resources/vendor/morrisjs/morris.css" rel="stylesheet">
      <!-- Custom Fonts -->
      <link href="${pageContext.request.contextPath}/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
      <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
      <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
      <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
      <![endif
      <!-- Other CSS -->
      <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/style.css" rel="stylesheet">
    
	
</head>
 
<body>
	<div id="wrapper">	
	  <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
		<header id="header">
			<tiles:insertAttribute name="header" />
		</header>
	
		<section id="sidemenu">
			<tiles:insertAttribute name="menu" />
		</section>
			
		<section id="site-content">
			<tiles:insertAttribute name="body" />
		</section>
		
		<footer id="footer">
			<tiles:insertAttribute name="footer" />
		</footer>
	
	</div>
		
		 <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
      <!-- Bootstrap Core JavaScript -->
      <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
      <!-- Metis Menu Plugin JavaScript -->
      <script src="${pageContext.request.contextPath}/resources/vendor/metisMenu/metisMenu.min.js"></script>
      <!-- Morris Charts JavaScript -->
      <script src="${pageContext.request.contextPath}/resources/vendor/raphael/raphael.min.js"></script>
      <script src="${pageContext.request.contextPath}/resources/vendor/morrisjs/morris.min.js"></script>
      <script src="${pageContext.request.contextPath}/resources/data/morris-data.js"></script>
      <!-- Custom Theme JavaScript -->
      <script src="${pageContext.request.contextPath}/resources/dist/js/sb-admin-2.js"></script>
      
</body>
</html>