package com.ylife.security;

import com.ylife.security.handler.SendJsonDecider;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

public class EntryPointSendJsonDecider implements SendJsonDecider {

	private static String DEFAULT_WEB_URL = "/backend/web/**";

	private static String DEFAULT_AJAX_URL = "/backend/web/api/**";

	private RequestMatcher webUrlMatcher = new AntPathRequestMatcher(DEFAULT_WEB_URL);

	private RequestMatcher ajaxUrlMatcher = new AntPathRequestMatcher(DEFAULT_AJAX_URL);

	public EntryPointSendJsonDecider() {
	}

	@Override
	public boolean decide(HttpServletRequest request) {
		if (webUrlMatcher.matches(request) && !ajaxUrlMatcher.matches(request)) {
			return false;
		}
		return true;
	}

	public void setWebUrl(String webUrl) {
		webUrlMatcher = new AntPathRequestMatcher(webUrl);
	}

	public void setAjaxUrl(String ajaxUrl) {
		ajaxUrlMatcher = new AntPathRequestMatcher(ajaxUrl);
	}

}
