package com.farkalit.webdemo.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;

@PropertySource("classpath:application.properties")
@Configuration
@EnableWebSecurity
@Order(1)
public class APISecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger LOG = LogManager.getLogger(APISecurityConfig.class);

	private static final String X_API_KEY = "x-api-key";

	@Value("${api.usman.service.authtoken}")
	private String principalRequestValue;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		APIKeySecurityFilter filter = new APIKeySecurityFilter(X_API_KEY);
		filter.setAuthenticationManager(new AuthenticationManager() {

			@Override
			public Authentication authenticate(Authentication authentication) {
				LOG.info("principalRequestValue:{}", principalRequestValue);
				String principal = (String) authentication.getPrincipal();
				LOG.info("principal:{}", principal);
				if (!principalRequestValue.equals(principal)) {
					throw new BadCredentialsException("The API key was not found or not the expected value.");
				}
				authentication.setAuthenticated(true);
				return authentication;
			}
		});
		httpSecurity.requestMatchers().antMatchers("/login/**", "/api/**").and().csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().addFilter(filter).authorizeRequests()
				.anyRequest().authenticated();
	}
}
