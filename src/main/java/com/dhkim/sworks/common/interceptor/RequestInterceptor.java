package com.dhkim.sworks.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

	protected transient Log logger = LogFactory.getLog(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.debug("============RequestInterceptor Start==================");
		logger.debug("getContextPath ============== [" + request.getContextPath() + "]");
		logger.debug("getRequestURI =============== [" + request.getRequestURI() + "]");
		//String[] uriArr = request.getRequestURI().replace("/WEB-INF/jsp/", "/").replaceAll("/+", "/").substring(1)
		//		.split("/");
		//logger.debug("========uriArr[0] = " + uriArr[0]);
		logger.debug("============RequestInterceptor End==================");
		return true;
		
	}
}
