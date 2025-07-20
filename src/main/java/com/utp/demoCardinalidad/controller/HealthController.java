package com.utp.demoCardinalidad.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 🏥 Controlador para verificar el estado de salud de la aplicación
 * 
 * @author demoCardinalidad
 * @version 1.0
 */
@RestController
public class HealthController {

    /**
     * 🔍 Endpoint para verificar el estado de salud de la aplicación
     * 
     * @return ResponseEntity con el estado de la aplicación
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("application", "demoCardinalidad");
        response.put("timestamp", System.currentTimeMillis());
        response.put("version", "1.0.0");
        response.put("port", System.getProperty("server.port", "8081"));
        response.put("java.version", System.getProperty("java.version"));
        
        // Información de variables de entorno para diagnóstico
        Map<String, String> env = new HashMap<>();
        env.put("PORT", System.getenv("PORT"));
        env.put("MYSQLHOST", System.getenv("MYSQLHOST"));
        env.put("MYSQLPORT", System.getenv("MYSQLPORT"));
        env.put("MYSQLDATABASE", System.getenv("MYSQLDATABASE"));
        env.put("MYSQLUSER", System.getenv("MYSQLUSER"));
        env.put("MYSQLPASSWORD", System.getenv("MYSQLPASSWORD") != null ? "***" : null);
        response.put("environment", env);
        
        return ResponseEntity.ok(response);
    }

    /**
     * 🏠 Endpoint raíz para verificar que la aplicación está funcionando
     * 
     * @return ResponseEntity con información básica
     */
    @GetMapping("/")
    public ResponseEntity<Map<String, String>> root() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "🚀 demoCardinalidad API está funcionando correctamente!");
        response.put("status", "UP");
        response.put("swagger", "/swagger-ui.html");
        response.put("health", "/health");
        response.put("test", "/test");
        
        return ResponseEntity.ok(response);
    }

    /**
     * 🧪 Endpoint de prueba simple
     * 
     * @return ResponseEntity con respuesta simple
     */
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("✅ Test endpoint funcionando!");
    }

    /**
     * 🔍 Endpoint de diagnóstico detallado
     * 
     * @return ResponseEntity con información de diagnóstico
     */
    @GetMapping("/diagnostic")
    public ResponseEntity<Map<String, Object>> diagnostic() {
        Map<String, Object> response = new HashMap<>();
        
        // Información del sistema
        Map<String, String> system = new HashMap<>();
        system.put("java.version", System.getProperty("java.version"));
        system.put("java.home", System.getProperty("java.home"));
        system.put("user.dir", System.getProperty("user.dir"));
        system.put("server.port", System.getProperty("server.port"));
        response.put("system", system);
        
        // Variables de entorno
        Map<String, String> env = new HashMap<>();
        env.put("PORT", System.getenv("PORT"));
        env.put("MYSQLHOST", System.getenv("MYSQLHOST"));
        env.put("MYSQLPORT", System.getenv("MYSQLPORT"));
        env.put("MYSQLDATABASE", System.getenv("MYSQLDATABASE"));
        env.put("MYSQLUSER", System.getenv("MYSQLUSER"));
        env.put("MYSQLPASSWORD", System.getenv("MYSQLPASSWORD") != null ? "***" : null);
        response.put("environment", env);
        
        // Información de memoria
        Runtime runtime = Runtime.getRuntime();
        Map<String, Object> memory = new HashMap<>();
        memory.put("total", runtime.totalMemory());
        memory.put("free", runtime.freeMemory());
        memory.put("used", runtime.totalMemory() - runtime.freeMemory());
        memory.put("max", runtime.maxMemory());
        response.put("memory", memory);
        
        response.put("timestamp", System.currentTimeMillis());
        response.put("status", "OK");
        
        // Información de la URL actual
        Map<String, String> urls = new HashMap<>();
        urls.put("swagger", "/swagger-ui.html");
        urls.put("api_docs", "/api-docs");
        urls.put("health", "/health");
        urls.put("test", "/test");
        response.put("endpoints", urls);
        
        return ResponseEntity.ok(response);
    }
} 