package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.example.demo.enums.Role;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
    private AccessDeniedHandler accessDeniedHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/gameLobby", "/error/**").permitAll()
				.antMatchers("/admin/**").hasAnyRole(Role.ADMIN.name())	
				.antMatchers("/user/**").hasAnyRole(Role.USER.name())	
				.anyRequest().authenticated()			
				.and()
			.formLogin().loginPage("/login")
			.permitAll()
			.defaultSuccessUrl("/index").and()
			.logout()
			.permitAll()
			.and()
			.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails admin =
				 User.withDefaultPasswordEncoder()
				.username("admin")
				.password("123")
				.roles(Role.ADMIN.name())
				.build();
		
		UserDetails user = User.withDefaultPasswordEncoder()
			.username("user")
			.password("321")
			.roles(Role.USER.name())
			.build();

		return new InMemoryUserDetailsManager(admin, user);
	}
}
