<%@include file="../taglib/taglib.jsp" %> 
 
 <style>
   td {
   border:none;
   }
 </style>
  
  <form action="${pageContext.request.contextPath}/login" method="post">
	<table style="width:100%">
	  <tr>
	       <td colspan="2" style="text-align: center; height: 100px;"><b> Login Page.</b></td>
	  </tr>
	    <tr>         
	       <td width="30%" style="border:1px solid #CCC;">
	        
	         <div class="error-login-fail">${errMsg}</div>
	             <table style="width:100%;">
	                <tr height="40px">
	                   <td style="text-align:right; vertical-align: top;" width="20%">Username</td>
	                   <td width="5%" style="text-align: center;vertical-align: top;">:</td>
	                   <td width="75%" style="vertical-align: top;"><input type="text" id="username" name="username"  tabindex="1" required="true"/></td>
	                </tr>	               
	                <tr height="40px">
	                   <td style="text-align:right; vertical-align: top;">Password</td>
	                   <td width="5%" style="text-align: center;vertical-align: top; ">:</td>
	                   <td style="vertical-align: top;"><input type="password"  id="password" name="password" tabindex="2" required="true"/></td>
	                </tr>	                
	                <tr> 
	                   <td colspan="3" width="5%" style="text-align: center;"> 
	                  	 <button type="submit" class="btn btn-info" >&nbsp;&nbsp;Login&nbsp;&nbsp;</button>
	                   </td>
	                </tr>
	                <tr> 
	                   <td colspan="3">&nbsp;</td>
	                </tr>
	                <tr> 
	                   <td colspan="3" style="text-align: center;"> <a href="#" tabindex="4" class="forgot-password">Forgot Password?</a></td>
	                </tr>
	             </table>
	             <input type="hidden" name="${_csrf.parameterName}"	value="${_csrf.token}" />
	       </td>
	    </tr>
	</table>
	</form>
	