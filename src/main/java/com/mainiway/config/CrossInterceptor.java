package com.mainiway.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CrossInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory.getLogger(CrossInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("请求地址为：----------" + request.getRequestURL());
//		logger.debug("请求参数为：----------" + body);
		response.addHeader("Access-Control-Allow-Origin", "*");
		 response.addHeader("Access-Control-Allow-Methods", "*");
		 response.addHeader("Access-Control-Max-Age", "100");
		 response.addHeader("Access-Control-Allow-Headers", "Content-Type");
		 response.addHeader("Access-Control-Allow-Credentials", "false");
		return super.preHandle(request, response, handler);
	}

}
