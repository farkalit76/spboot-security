package com.farkalit.webdemo.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class APIKeySecurityFilter extends AbstractPreAuthenticatedProcessingFilter {

	private static final Logger LOG = LogManager.getLogger(APIKeySecurityFilter.class);
	
	private String principalRequestHeader;

	public APIKeySecurityFilter(String principalRequestHeader) {
		this.principalRequestHeader = principalRequestHeader;
	}

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		LOG.info("getPreAuthenticatedPrincipal request:{}", request);
		return request.getHeader(principalRequestHeader);
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		LOG.info("getPreAuthenticatedPrincipal request:{}", request);
		return "N/A";
	}

}
