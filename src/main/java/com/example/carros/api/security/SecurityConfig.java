package com.example.carros.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
//permite utilizar a notação @secured (usamos no controller), visando habilitar a segurança por método
@EnableGlobalMethodSecurity(securedEnabled = true) 
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//se olharmos a implementação padrão que está sendo sobrescrita aqui, ela ativa tanto o http basic 
		//quanto o form login. para a nossa API, só queremos o basic
		http
		.authorizeRequests()
			.anyRequest().authenticated()
			.and().httpBasic()
			.and().csrf().disable(); //podemos desligar, pq uma chamada para API é stateless
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);

	}
}
