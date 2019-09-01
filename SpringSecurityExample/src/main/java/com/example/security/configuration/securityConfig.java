package com.example.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Here we can have our own authentication on the auth object
		auth.inMemoryAuthentication()//
				.withUser("lav").password("lav").roles("manager")//
				.and()//
				.withUser("joseph").password("joseph").roles("user");
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		// Dummy it is still dealing with hard coded password
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		http.authorizeRequests()//
				.antMatchers("/manager").hasRole("manager")//
				.antMatchers("/user").hasAnyRole("user", "manager")//
				.antMatchers("/").permitAll()//
				.and()//
				.formLogin();

	}

}
