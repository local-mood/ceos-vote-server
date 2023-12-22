package com.ceos.vote.common.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {

    Info info = new Info()
      .title("ceos-vote-api")
      .description("CEOS 파트장∙팀 투표 서비스")
      .version("1.0.0");

    SecurityScheme basicAuth = new SecurityScheme()
      .type(Type.HTTP)
      .scheme("Bearer")
      .bearerFormat("JWT")
      .in(In.HEADER)
      .name("Authorization");

    SecurityRequirement securityItem = new SecurityRequirement().addList("basicAuth");

    return new OpenAPI()
      .components(new Components().addSecuritySchemes("basicAuth", basicAuth))
      .addSecurityItem(securityItem)
      .info(info);

  }
}
