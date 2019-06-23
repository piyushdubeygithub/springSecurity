package com.prosmv.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import com.prosmv.filter.TokenAuthenticationFilter;
import com.prosmv.repositories.AuthenticationTokenRepository;
import com.prosmv.util.URLMapping;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

//	@Autowired//
//	UserDetailsService userDetailsService;
//	
//	@Bean
//	public AuthenticationProvider authProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(userDetailsService);
//		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//		return provider;
//	}
	
//	@Bean
//	@Override
//	protected UserDetailsService userDetailsService() {
//     List<UserDetails> users = new ArrayList<>();
//     users.add(User.withDefaultPasswordEncoder().username("piyush").password("12345").roles("USER").build());
//	 return new InMemoryUserDetailsManager(users);
//	}

	   @Autowired
	    private AuthenticationTokenRepository authenticationTokenRepository;
	  
	    @Override
	    protected void configure(final HttpSecurity http) throws Exception
	    {

	        //Implementing Token based authentication in this filter
	    	http.csrf().disable().
			sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    	http.authorizeRequests().antMatchers(HttpMethod.POST,URLMapping.USER_LOGIN).permitAll()
		    .antMatchers(HttpMethod.GET,URLMapping.COMPANY_LIST).hasRole("USER").anyRequest().authenticated()
		    .antMatchers(HttpMethod.GET,URLMapping.REGISTER_USER).hasRole("USER").anyRequest().authenticated()
		    .antMatchers(HttpMethod.GET,URLMapping.UPDATE_USER).hasRole("USER").anyRequest().authenticated()
		    .antMatchers(HttpMethod.GET,URLMapping.REGISTER_COMPANY).hasRole("USER").anyRequest().authenticated()
		    .antMatchers(HttpMethod.GET,URLMapping.UPDATE_COMPANY).hasRole("USER").anyRequest().authenticated()
		    .antMatchers(HttpMethod.GET,URLMapping.USER_LIST).hasRole("USER").anyRequest().authenticated()
		    .antMatchers(HttpMethod.GET,URLMapping.DELETE_COMPANY).hasRole("USER").anyRequest().authenticated()
		    .antMatchers(HttpMethod.GET,URLMapping.DEACTIVATE_COMPANY).hasRole("USER").anyRequest().authenticated()
		    .antMatchers(HttpMethod.GET,URLMapping.ACTIVATE_COMPANY).hasRole("USER").anyRequest().authenticated()
		    .antMatchers(HttpMethod.GET,URLMapping.REGISTER_FACTORY).hasRole("USER").anyRequest().authenticated()
		    .antMatchers(HttpMethod.GET,URLMapping.UPDATE_FACTORY).hasRole("USER").anyRequest().authenticated()
		    .antMatchers(HttpMethod.GET,URLMapping.FACTORY_LIST).hasRole("USER").anyRequest().authenticated()
		    .antMatchers(HttpMethod.GET,URLMapping.DEACTIVATE_FACTORY).hasRole("USER").anyRequest().authenticated()
		    .antMatchers(HttpMethod.GET,URLMapping.ACTIVATE_FACTORY).hasRole("USER").anyRequest().authenticated();

			// Implementing Token based authentication in this filter
		   final TokenAuthenticationFilter tokenFilter = new TokenAuthenticationFilter(authenticationTokenRepository);
		  http.addFilterBefore(tokenFilter, BasicAuthenticationFilter.class);
		}
	    
	    @Override
	    public void configure(WebSecurity web) throws Exception {
	    	web.ignoring().antMatchers("/v2/api-docs/**");
	    	web.ignoring().antMatchers("/swagger.json");
	    	web.ignoring().antMatchers("/swagger-ui.html");
	    	web.ignoring().antMatchers("/webjars/**");
	    	web.ignoring().antMatchers("/swagger-resources/**");
	    }

}
