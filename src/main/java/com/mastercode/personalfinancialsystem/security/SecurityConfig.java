package com.mastercode.personalfinancialsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
    @Autowired
    private AuthenticationSuccessHandler mySuccessHandler;	
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin").password(encoder().encode("adminPass")).roles("ADMIN")
			.and()
			.withUser("user").password(encoder().encode("userPass")).roles("USER");
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { 
	    http
	    .csrf().disable()
	    .exceptionHandling()
		    .authenticationEntryPoint(restAuthenticationEntryPoint)
		    .and()
		    
	    .authorizeRequests()
		    .antMatchers("/home").permitAll()
		    .antMatchers("/users/**").authenticated()
		    .and()
		    
	    .formLogin()	    
		    .successHandler(mySuccessHandler)
		    .failureHandler(new SimpleUrlAuthenticationFailureHandler())
		    .and()
		    
	    .httpBasic()
	    .and()
	    .logout();
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
