package com.farkalit.security.auth.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableResourceServer
@Configuration
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

	@Value("${user.oauth.user.username}")
    private String username;
    @Value("${user.oauth.user.password}")
    private String password;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("ResourceServerConfig.configure httpsecurity");
		http.requestMatchers()
			.antMatchers("/login", "/oauth/authorize")
			.and()
			.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.formLogin().permitAll();
		
//		http.requestMatchers()
//			.antMatchers("/login", "/oauth/authorize")
//			.antMatchers("/resources/")
//			.and()
//			.authorizeRequests()
//			.antMatchers("/resources/**").permitAll()
//			.anyRequest().authenticated()
//			.and().exceptionHandling()
//			.accessDeniedPage("/login?authorization_error=true")
//			.and()
//			.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize")).disable()
//			.formLogin()
//			.loginProcessingUrl("/login")
//			.failureUrl("/login?authentication_error=true")
//			.loginPage("/login").permitAll()
//			.and()
//			.csrf().disable()
//			.logout().logoutSuccessUrl("/login?logout")
//			.and()
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
		
		
//		http.headers().frameOptions().deny()
//			.and()
//			.authorizeRequests()
//			.antMatchers("/login", "/oauth/authorize").permitAll()
//			.antMatchers("/resources/**").permitAll() .anyRequest().authenticated()
//			.and()
//			.exceptionHandling()
//			.accessDeniedPage("/login?authorization_error=true")
//			.and()
//			.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
//			.disable()
//			.formLogin()
//			.loginProcessingUrl("/login")
//			.failureUrl("/login?authentication_error=true") .loginPage("/login")
//			.and()
//			.logout().logoutSuccessUrl("/login?logout") .and()
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
			
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("ResourceServerConfig.configure auth builder");
		auth.inMemoryAuthentication()
        .withUser(username)
        .password(passwordEncoder().encode(password))
        .roles("USER");
	}

	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}