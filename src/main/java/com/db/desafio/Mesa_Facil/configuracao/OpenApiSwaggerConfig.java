package com.db.desafio.Mesa_Facil.configuracao;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiSwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Desafio Mesa fácil")
                        .version("v1")
                        .description(
                                "API REST para gerenciar reservas de mesas em restaurantes."
                        )
                        .contact(new Contact()
                                .name("Saulo Henrique Rodrigues")
                                .email("saulo.rodrigues@db.tec.br"))
                )
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local")
                ));
    }
}