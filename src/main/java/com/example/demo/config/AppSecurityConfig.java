package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.enums.Role;
import com.example.demo.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{

	private static Logger logger = LoggerFactory.getLogger(AppSecurityConfig.class);
	@Autowired
    private AccessDeniedHandler accessDeniedHandler;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.warn("reload security configs");
		http.authorizeRequests()
				.antMatchers("/webjars/**", "/css/**", "/fonts/**", "/libs/**", "/js/**"
						, "/", "/gameLobby", "/error/**", "/register", "/process_register"
						, "/mainGame", "/mainGame/**", "/mainGame?**", "/playGame").permitAll()
				.antMatchers("/admin", "/admin/**", "/userList").hasAnyAuthority(Role.ADMIN.name())	
				.antMatchers("/user/**").hasAnyAuthority(Role.USER.name())	
				.anyRequest().authenticated()			
				.and()
			.formLogin().loginPage("/login").usernameParameter("email")
			.permitAll()
			.defaultSuccessUrl("/index").and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
			.and()
			.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
//		UserDetails admin =
//				 User.withDefaultPasswordEncoder()
//				.username("admin")
//				.password("123")
//				.roles(Role.ADMIN.name())
//				.build();
//		
//		UserDetails user = User.withDefaultPasswordEncoder()
//			.username("user")
//			.password("321")
//			.roles(Role.USER.name())
//			.build();

//		return new InMemoryUserDetailsManager(admin);
		return new CustomUserDetailsService();
	}
	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
}
