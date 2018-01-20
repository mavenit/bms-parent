package com.bms.eai.portal.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kul_sudhakar
 *
 */
public class CsrfHeaderFilter /*extends OncePerRequestFilter*/ {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	//@Override
	/*protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		logger.info("[Comes to CsrfHeaderFilter]");
		
		CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
		logger.info("[CsrfToken :"+csrf+"]");
		
		if (csrf != null) {
			logger.info("[Not Null csrf] >>>>>>>>");
			Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
			logger.info("[cookie.getValue() :"+cookie.getValue()+"]");
			String token = csrf.getToken();
			logger.info("[csrf.getToken() :"+token+"]");
			
			if (cookie == null || token != null && !token.equals(cookie.getValue())) {
				cookie = new Cookie("XSRF-TOKEN", token);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
		filterChain.doFilter(request, response);
	}*/

}
