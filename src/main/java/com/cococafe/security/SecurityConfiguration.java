package com.cococafe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.inMemoryAuthentication()
			.passwordEncoder(NoOpPasswordEncoder.getInstance())
			.withUser("Neha").password("pass")
			.roles("USER", "ADMIN");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.authorizeRequests().antMatchers("/login").permitAll()
			.antMatchers("/db/**").permitAll()
			.antMatchers("/","/orderHistory", "/*Order*/**").access("hasRole('USER')").and()
			.formLogin().loginPage("/login")
			.and().csrf().ignoringAntMatchers("/db/**")
			.and().headers().frameOptions().sameOrigin();
	}

}
