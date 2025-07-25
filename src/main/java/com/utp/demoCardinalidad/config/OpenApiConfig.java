package com.utp.demoCardinalidad.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        // Servidor local para desarrollo
        Server localServer = new Server()
                .url("http://localhost:8080")
                .description("Servidor Local (Desarrollo)");

        // Servidor de Railway para producción
        Server railwayServer = new Server()
                .url("https://humble-endurance-production-d1c9.up.railway.app")
                .description("Servidor Railway (Producción)");

        Contact contact = new Contact()
                .email("contacto@example.com")
                .name("Demo Cardinalidad API")
                .url("https://github.com/yourusername/demoCardinalidad");

        License mitLicense = new License()
                .name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Demo Cardinalidad API")
                .version("1.0")
                .contact(contact)
                .description("API para demostrar relaciones de cardinalidad con Spring Boot")
                .license(mitLicense);

        return new OpenAPI()
                .info(info)
                .servers(List.of(railwayServer, localServer));
    }
} 