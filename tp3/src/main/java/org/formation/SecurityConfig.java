package org.formation;

import org.formation.jwt.JWTFilter;
import org.formation.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	TokenProvider tokenProvider;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**","/swagger-resources/**","**/*.css","**/*.js");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
				
		/** Implémentation sécurité stateful
		 * 
		 */
//		http.csrf().disable()
//			.authorizeRequests()
//			.antMatchers("/swagger-ui.html","/swagger-ui/**","/v3/api-docs/**").permitAll()
//		    .antMatchers(HttpMethod.GET,"/api/**").authenticated()
//		    .antMatchers("/api/**").hasRole("ADMIN")
//		    .anyRequest().authenticated()
//		    .and()
//		    .formLogin()
//		    .and()
//		    .logout().invalidateHttpSession(true);

		http
        .csrf().disable() // Jeton csrf n’est plus nécessaire
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    .and()
        .authorizeRequests() // ACLs
        .antMatchers("/api/authenticate").permitAll()
        .antMatchers("/actuator/**").permitAll()
        .antMatchers("/swagger-ui.html","/swagger-ui/**","/v3/api-docs/**").permitAll()
	    .antMatchers(HttpMethod.GET,"/api/**").authenticated()
	    .antMatchers("/api/**").hasRole("ADMIN")
        .anyRequest().authenticated()
    .and()
    	.addFilterBefore(new JWTFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);


	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("{noop}secret").authorities("ROLE_USER").and()
									.withUser("admin").password("{noop}secret").authorities("ROLE_ADMIN");
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}




	
	
}
