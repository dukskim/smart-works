package com.dhkim.sworks.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dhkim.sworks.sql.domain.UserInfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	protected transient Log logger = LogFactory.getLog(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		logger.debug("============LoginCheckInterceptor Start==================");

		HttpSession session = request.getSession();
		//String uri = request.getRequestURI();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		// System.out.println("uri : " + uri);
		
		boolean result = true;
		
		//if (uri.indexOf("login/") < 0) {
			if ((userInfo == null) || userInfo.getUserId().equals("")) {
				response.sendRedirect("/login/loginView.do");
				
				result = false;
			}
		//}
		logger.debug("result : " + result);
		logger.debug("============LoginCheckInterceptor End==================");

		return result;
	}
}
