package com.example.vikash.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.example.vikash.domain.AuthenticationToken;
import com.example.vikash.domain.CustomUserDetails;
import com.example.vikash.repository.AuthenticationTokenRepository;

public class TokenAuthenticationFilter extends GenericFilterBean
{
	
	private AuthenticationTokenRepository authenticationTokenRepository;
	public TokenAuthenticationFilter(AuthenticationTokenRepository authenticationTokenRepository){
		this.authenticationTokenRepository = authenticationTokenRepository;
	}
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException
    {
        final HttpServletRequest httpRequest = (HttpServletRequest)request;

         //extract token from header
        final String accessToken = httpRequest.getHeader("header-name");
        if (null != accessToken) {
        	AuthenticationToken auth=authenticationTokenRepository.findByToken(accessToken);
        	//get and check whether token is valid ( from DB or file wherever you are storing the token)

            //Populate SecurityContextHolder by fetching relevant information using token
                CustomUserDetails customUserDetails =new CustomUserDetails(auth.getUser());
                      final UsernamePasswordAuthenticationToken authentication =
                              new UsernamePasswordAuthenticationToken(auth.getUser(), null, customUserDetails.getAuthorities());
                      
                      SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        chain.doFilter(request, response);
    }

  }
