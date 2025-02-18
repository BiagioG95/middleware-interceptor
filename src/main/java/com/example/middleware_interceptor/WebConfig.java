package com.example.middleware_interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Autowired
    private TimingInterceptor timingInterceptor;
    @Autowired
    private  APILoggingInterceptor apiLoggingInterceptor;
    @Autowired
    private LegacyIntercepotr legacyIntercepotr;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timingInterceptor)
                .addPathPatterns("/**"); // Applica a tutte le URL
        registry.addInterceptor(apiLoggingInterceptor)
                .addPathPatterns("/basic");
        registry.addInterceptor(legacyIntercepotr)
                .addPathPatterns("/legacy");
    }
}
