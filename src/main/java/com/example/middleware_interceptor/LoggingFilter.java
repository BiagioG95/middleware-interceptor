package com.example.middleware_interceptor;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        
        System.out.println("[FILTER] Prima della richiesta: " + requestURI);
        
        long startTime = System.currentTimeMillis();
        
        // Passa la richiesta al filtro successivo o al DispatcherServlet
        chain.doFilter(request, response);
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("[FILTER] Dopo la richiesta: " + requestURI);
        System.out.println("[FILTER] Tempo di esecuzione: " + (endTime - startTime) + "ms");
    }
}
