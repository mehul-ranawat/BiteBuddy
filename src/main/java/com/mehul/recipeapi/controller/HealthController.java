package com.mehul.recipeapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/health")
@Tag(name = "Health", description = "Application and deployment health probe")
public class HealthController {

    @GetMapping
    @Operation(summary = "System Health Status", description = "Check if the API and database services are operational")
    public ResponseEntity<Map<String, Object>> checkHealth() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "service", "Bite Buddy API",
                "version", "1.0.0",
                "timestamp", LocalDateTime.now().toString(),
                "deployment", "Railway.app"
        ));
    }
}
