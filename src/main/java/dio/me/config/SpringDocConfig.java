package dio.me.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Restful API")
                        .description("API Springboot RESTFUL - " +
                                "Responsável por controle de consultas, Pacientes e Relatórios.")
                        .contact(new Contact()
                                .name("Reynaldo Hendson")
                                .email("reynaldohendsondev@outlook.com")
                        )
                );
    }

}
