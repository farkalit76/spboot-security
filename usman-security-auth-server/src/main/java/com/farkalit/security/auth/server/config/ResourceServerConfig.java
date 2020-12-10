package com.farkalit.security.auth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableResourceServer
@Configuration
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("ResourceServerConfig.configure httpsecurity");
//		http.requestMatchers()
//			.antMatchers("/login", "/logout", "/oauth/authorize", "/oauth/confirm_access")
//			//.and().cors().configurationSource(configurationSource())
//			.and()
//			.authorizeRequests()
//			.anyRequest()
//			.authenticated()
//			.and()
//			.formLogin()
//			.permitAll();
		
		http.requestMatchers()
			.antMatchers("/login", "/oauth/authorize")
			.antMatchers("/resources/**")
			.and()
			.authorizeRequests()
			.antMatchers("/resources/**").permitAll()
			.anyRequest().authenticated()
			.and().exceptionHandling()
			.accessDeniedPage("/login?authorization_error=true")
			.and()
			.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize")).disable()
			.formLogin()
			.loginProcessingUrl("/login")
			.failureUrl("/login?authentication_error=true")
			.loginPage("/login").permitAll()
			.and()
			 .csrf().disable() 
			.logout().logoutSuccessUrl("/login?logout")
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);


	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("ResourceServerConfig.configure auth builder");
		auth.parentAuthenticationManager(this.authenticationManager).inMemoryAuthentication().withUser("Usman")
				.password("usman").roles("USER");
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		System.out.println("ResourceServerConfig.authenticationManagerBean");
		return super.authenticationManagerBean();
	}
	
//	private CorsConfigurationSource configurationSource() {
//		  UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		  CorsConfiguration config = new CorsConfiguration();
//		  config.addAllowedOrigin("*");
//		  config.setAllowCredentials(true);
//		  config.addAllowedHeader("X-Requested-With");
//		  config.addAllowedHeader("Content-Type");
//		  config.addAllowedMethod(HttpMethod.POST);
//		  source.registerCorsConfiguration("/logout", config);
//		  return source;
//		}
	
}