package com.covidpersona.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Exception exception = (Exception) httpServletRequest.getAttribute("exception");

        String message;
        if (exception!=null){
            if (exception.getCause()!=null){
                message = exception.getCause().toString() + " " +exception.getMessage();
            }else {
                message = exception.getMessage();
            }

            byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("error",message));
            httpServletResponse.getOutputStream().write(body);
        }else{
            if (e.getCause()!=null){
                message = e.getCause().toString() + " " +e.getMessage();
            }else {
                message = e.getMessage();
            }

            byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("error",message));
            httpServletResponse.getOutputStream().write(body);
        }
    }
}

