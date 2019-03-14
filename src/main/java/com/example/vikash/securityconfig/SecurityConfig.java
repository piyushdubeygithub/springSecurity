package com.example.vikash.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.vikash.filter.TokenAuthenticationFilter;
import com.example.vikash.repository.AuthenticationTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private AuthenticationTokenRepository authenticationTokenRepository;
    @Override
    protected void configure(final HttpSecurity http) throws Exception
    {

        //Implementing Token based authentication in this filter
    	http.csrf().disable().
//		sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		authorizeRequests().antMatchers(HttpMethod.POST,"/api/v1/user/login").permitAll().antMatchers(HttpMethod.POST,"/api/v1/user/signup").permitAll()
		.antMatchers(HttpMethod.GET,"/api/v1/user/getallUser/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/api/v1/user/get/byId").hasRole("USER")
		.antMatchers(HttpMethod.PUT,"/api/v1/user/update").hasRole("USER")
		.antMatchers(HttpMethod.PUT,"/api/v1/user/change/password").hasRole("USER")
		.antMatchers(HttpMethod.POST,"/api/v1/user/forget/password").hasRole("USER").anyRequest().authenticated();
		// Implementing Token based authentication in this filter
		final TokenAuthenticationFilter tokenFilter = new TokenAuthenticationFilter(authenticationTokenRepository);
		http.addFilterBefore(tokenFilter, BasicAuthenticationFilter.class);
	}
}

