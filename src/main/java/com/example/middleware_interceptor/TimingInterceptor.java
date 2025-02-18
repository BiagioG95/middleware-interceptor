package com.example.middleware_interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TimingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        String requestURI = request.getRequestURI();
        System.out.println("[INTERCEPTOR] preHandle - URI: " + requestURI);
        
        // Memorizza il tempo di inizio nella request
        request.setAttribute("startTime", System.currentTimeMillis());
        
        return true; // true consente di continuare, false interrompe la catena
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                          ModelAndView modelAndView) throws Exception {
        
        String requestURI = request.getRequestURI();
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        
        System.out.println("[INTERCEPTOR] postHandle - URI: " + requestURI);
        System.out.println("[INTERCEPTOR] Tempo di esecuzione controller: " + (endTime - startTime) + "ms");
        
        // Se c'è una vista, aggiungi informazioni
        if (modelAndView != null) {
            modelAndView.addObject("executionTime", (endTime - startTime));
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                               Exception ex) throws Exception {
        
        String requestURI = request.getRequestURI();
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        
        System.out.println("[INTERCEPTOR] afterCompletion - URI: " + requestURI);
        System.out.println("[INTERCEPTOR] Tempo totale: " + (endTime - startTime) + "ms");
        
        if (ex != null) {
            System.out.println("[INTERCEPTOR] Eccezione: " + ex.getMessage());
        }
    }
}
