package org.formation;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


public class ACLSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/swagger-ui/**","/v3/api-docs/**");
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		    .authorizeRequests()
		   .antMatchers("/swagger-ui.html").permitAll()
		   .antMatchers(HttpMethod.GET, "/api/**").authenticated().antMatchers("/api/**").hasRole("ADMIN")
		   .anyRequest().authenticated()
		   .and()
		   .formLogin()
		   .and()
		   .logout().logoutSuccessUrl("/home").logoutUrl("/logout").invalidateHttpSession(true).permitAll();
	}
	


}
