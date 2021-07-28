package org.formation;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**","/swagger-resources/**","**/*.css","**/*.js");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
				
		/** Implémentation sécurité stateful
		 * 
		 */
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/swagger-ui.html","/swagger-ui/**","/v3/api-docs/**").permitAll()
		    .antMatchers(HttpMethod.GET,"/api/**").authenticated()
		    .antMatchers("/api/**").hasRole("ADMIN")
		    .anyRequest().authenticated()
		    .and()
		    .formLogin()
		    .and()
		    .logout().invalidateHttpSession(true);


	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("{noop}secret").authorities("ROLE_USER").and()
									.withUser("admin").password("{noop}secret").authorities("ROLE_ADMIN");
	}




	
	
}
