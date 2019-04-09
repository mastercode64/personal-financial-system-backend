package com.mastercode.personalfinancialsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.mastercode.personalfinancialsystem.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
    @Autowired
    private AuthenticationSuccessHandler mySuccessHandler;
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
    
    @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
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
		    .antMatchers(HttpMethod.POST, "/users").permitAll()
		    .antMatchers("/**").authenticated()
		    .and()
		    
	    .formLogin()	    
		    .successHandler(mySuccessHandler)
		    .failureHandler(new SimpleUrlAuthenticationFailureHandler())
		    .and()
		    
	    .httpBasic()
	    .and()
	    .logout();
	}
}
