package com.pad.springboot.groceryinventory.security;

import org.apache.catalina.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public InMemoryUserDetailsManager userDetailManager() {
		
		UserDetails sreepad = User.builder()
				.username("sreepad")
				.password("{noop}020992")
				.roles("ADMIN")
				.build();
		
		
		return new InMemoryUserDetailsManager(sreepad);
		
	}
}
