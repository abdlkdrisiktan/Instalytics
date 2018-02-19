package com.mongodbconnection.demo.Config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("Authenticate","Basic :  "+  getRealmName());

        PrintWriter writer = response.getWriter();
        writer.println("HTTP STATUS  401  : "+authException.getMessage());

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("MY_TEST_REALM");
        super.afterPropertiesSet();
    }
}
