package com.dhkim.sworks.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class MenuSettingInterceptor extends HandlerInterceptorAdapter {
	
	protected transient Log logger = LogFactory.getLog(this.getClass());
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.debug("============MenuSettingInterceptor Start==================");
		super.postHandle(request, response, handler, modelAndView);
		try {
			if (modelAndView != null){
				String menu = (String) modelAndView.getModelMap().get("menuId");
				if (menu != null) {
					HttpSession session = request.getSession();
					session.setAttribute("menu", menu);
				}
			}
		} catch (Exception e) {
		}
		logger.debug("============MenuSettingInterceptor End==================");
	}
}
