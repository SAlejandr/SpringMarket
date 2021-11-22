package com.example.demo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new AuthenticationSuccessHandlerImp();
    }
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		super.configure(http);
		
		http.
		 authorizeRequests().
		 antMatchers("/","/indesx","/index","/js/**","/css/**","/images/**","/signup","/createUser","/webjars/**").permitAll().
		 antMatchers("/admin/**").hasAuthority("admin").
		 anyRequest().authenticated().
		 and().
		 formLogin().
		 loginPage("/usuario/login").
		 loginProcessingUrl("/usuario/login").
		 usernameParameter("email").
		 successHandler(myAuthenticationSuccessHandler()).
		 permitAll().
		 and().
		 logout().
		 invalidateHttpSession(true).
		 deleteCookies("JSESSIONID").
		 clearAuthentication(true).
		 logoutUrl("/usuario/logout").
		 logoutRequestMatcher(new AntPathRequestMatcher("/usuario/logout")).
		 logoutSuccessUrl("/usuario/login?logout").
		 permitAll();
		
	}
	
	
	 @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
	 
	        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
	    }
	 
}
