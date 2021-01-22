package org.formation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@Profile("memorySecurity")
public class MemorySecurityConfig extends ACLSecurityConfig {




	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("user").password("{noop}secret").roles("USER")
		.and()
		.withUser("admin").password("{noop}secret").roles("ADMIN");	
	}
}
