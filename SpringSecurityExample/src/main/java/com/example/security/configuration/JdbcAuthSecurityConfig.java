package com.example.security.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@EnableWebSecurity
public class JdbcAuthSecurityConfig extends WebSecurityConfigurerAdapter {

	/*- This will provide the spring auto configured dataSource
	 *  however, if you want to configure your own dataSource then
	 *  checkout on my another GitHub Link example below on how to do it ;)
	 *  
	 *  https://github.com/lavkherwa/AllAboutREST/blob/master/AllAboutRest/src/main/java/com/example/rest/api/configurations/DataSourceConfig.java
	 */
	private final DataSource dataSource;

	public JdbcAuthSecurityConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		/*- We have schema and data at resource folder now 
		 *  schema.sql and data.sql
		 *
		 *  Documentation for default schema
		 *  https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#user-schema 
		 *  
		 */
		auth.jdbcAuthentication()//
				.dataSource(dataSource);

		/*- If you want to work with different schema and don't want to got with the 
		 *  default, then you can use name Query to query your own DB tables and provide 
		 *  spring security the required details as below
		 * 
		 * auth.jdbcAuthentication()//
		 *     .dataSource(dataSource)//
		 *     .usersByUsernameQuery(" select username,password,enabled "
		 *                         + " from users " 
		 *                         + " where username = ?")
		 *     .authoritiesByUsernameQuery(" select username,authority " 
		 *                         + " from authorities "
		 *                         + " where username = ?");
		 *                         
		 */

		/*- This can be used for testing, no in ideal production scenarios
		 
				// This will have H2 database populate with predefined tables then you can add
				// user authentication details to it
				 
					.withDefaultSchema()//
					.withUser(//
							User.withUsername("lav")//
									.password("lav")//
									.roles("MANAGER")//
					)//
					.withUser(//
							User.withUsername("joseph")//
									.password("joseph")//
									.roles("USER")//
					);	
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
				.antMatchers("/manager").hasRole("MANAGER")//
				.antMatchers("/user").hasAnyRole("MANAGER", "USER")//
				.antMatchers("/").permitAll()//
				.and()//
				.formLogin();
	}

}
