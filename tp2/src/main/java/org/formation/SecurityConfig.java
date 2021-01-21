package org.formation;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/swagger-ui/**","/v3/api-docs/**");
	}

	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
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
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("user").password("{noop}secret").roles("USER")
		.and()
		.withUser("admin").password("{noop}secret").roles("ADMIN");	
	}

}
