package com.example.vikash.filter;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class CustomBasicAuthenticationFilter extends BasicAuthenticationFilter {


    @Autowired
    public CustomBasicAuthenticationFilter(final AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    protected void onSuccessfulAuthentication(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response, final Authentication authResult) {
        //Generate Token
        //Save the token for the logged in user
        //send token in the response
        response.setHeader("header-name" , "token");


    }

}
