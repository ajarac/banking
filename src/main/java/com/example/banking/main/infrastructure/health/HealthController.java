package com.example.banking.main.infrastructure.health;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    ResponseEntity<Void> health() {
        return ResponseEntity.ok().build();
    }

}
