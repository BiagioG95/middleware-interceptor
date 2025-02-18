package com.example.middleware_interceptor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @GetMapping("/test")
    public String testEndpoint() throws InterruptedException {
        // Simuliamo un'operazione che richiede tempo
        Thread.sleep(500);
        return "Test completato con successo!";
    }
    
    @GetMapping("/error-test")
    public String errorEndpoint() {
        // Simuliamo un errore
        throw new RuntimeException("Eccezione di test");
    }
}
