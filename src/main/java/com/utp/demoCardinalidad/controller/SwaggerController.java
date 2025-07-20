package com.utp.demoCardinalidad.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 📚 Controlador para Swagger UI
 * 
 * @author demoCardinalidad
 * @version 1.0
 */
@RestController
public class SwaggerController {

    /**
     * 🔍 Endpoint para verificar la configuración de Swagger
     * 
     * @return ResponseEntity con información de Swagger
     */
    @GetMapping("/swagger-info")
    public ResponseEntity<Map<String, Object>> swaggerInfo() {
        Map<String, Object> response = new HashMap<>();
        response.put("swagger_ui_url", "/swagger-ui.html");
        response.put("api_docs_url", "/api-docs");
        response.put("api_docs_json_url", "/api-docs.json");
        response.put("message", "Swagger UI está configurado correctamente");
        response.put("status", "OK");
        
        return ResponseEntity.ok(response);
    }

    /**
     * 🔧 Endpoint para redirigir a Swagger UI
     * 
     * @return ResponseEntity con redirección
     */
    @GetMapping("/docs")
    public ResponseEntity<Map<String, String>> docs() {
        Map<String, String> response = new HashMap<>();
        response.put("swagger_ui", "/swagger-ui.html");
        response.put("api_docs", "/api-docs");
        response.put("message", "Usa /swagger-ui.html para acceder a la documentación");
        
        return ResponseEntity.ok(response);
    }
} 