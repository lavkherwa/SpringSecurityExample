package com.example.security.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Here we can have our own authentication on the auth object
		auth.inMemoryAuthentication()//
				.withUser("lav").password("lav").authorities("role_manager")//
				.and()//
				.withUser("joseph").password("joseph").authorities("role_user");

		/*- We can configure using JDBC as well 
		 * 
		 *  Note: Following table should be available in the DB
		 *  users-> username, password, enabled
		 *  authorities -> username, authority
		 * 
		 *   // Implementation code
		 *   auth.jdbcAuthentication()//
		 *		 .dataSource(dataSource)//
		 *		 .passwordEncoder(getPasswordEncoder());
		 */

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
				.antMatchers("/user").access("hasRole('manager') AND hasRole('user')")//
				.antMatchers("/").permitAll()//
				.and()//
				.formLogin();

	}

}
