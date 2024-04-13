package com.iprody08.inquiryservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*Access to user interface Swagger
http://localhost:8080/swagger-ui/index.html
* */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "REST API Inquiry Service",
                version = "1.0",
                description = """
                        Inquiry Service application.
                        """,
                contact = @Contact(
                        name = "Bogdanovich Pavel",
                        email = "sega0172@gmail.com"
                )
        )
)

public class OpenAPIConfiguration {
    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("REST API")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public GroupedOpenApi actuatorApi() {
        return GroupedOpenApi.builder()
                .group("Actuator")
                .pathsToMatch("/actuator/**")
                .build();
    }
}
