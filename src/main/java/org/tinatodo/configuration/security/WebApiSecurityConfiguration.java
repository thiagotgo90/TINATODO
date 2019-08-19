package org.tinatodo.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebApiSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	private UserDetailsService todoUserDetailService;
	
	private PasswordEncoder encoder;
	
	@Autowired
	public WebApiSecurityConfiguration(UserDetailsService todoUserDetailService, PasswordEncoder encoder) {
		this.todoUserDetailService = todoUserDetailService;
		this.encoder = encoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
			.antMatchers("/api/**").hasRole("USER")
			.anyRequest().permitAll()
		.and()
			.httpBasic()
		.and()
			.csrf().disable();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.
			userDetailsService(todoUserDetailService)
			.passwordEncoder(encoder);
	}

}
