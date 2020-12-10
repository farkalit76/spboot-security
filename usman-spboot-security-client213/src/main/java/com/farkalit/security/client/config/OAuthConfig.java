package com.farkalit.security.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class OAuthConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("OAuthConfig.configure");
		http.antMatcher("/**").authorizeRequests()
			.antMatchers("/", "/login**").permitAll()
			.anyRequest().authenticated()
			.and()
            .oauth2Login();
				
	}

}
