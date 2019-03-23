package ie.altech.demo.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Configuration
@Configuration
// Enable Swagger
@EnableSwagger2
public class SwaggerConfig {

    private static final String API_INFO_LICENSE = "Apache 2.0";
    private static final String API_INFO_DESCRIPTION = "This is Demo Api from in28minutes";
    private static final String API_INFO_LICENSE_URL = "http://www.apache.org/licenses/LICENSE-2.0";
    private static final String API_INFO_CONTACT_NAME = "Altech";
    private static final String API_INFO_CONTACT_URL = "altech.com";
    private static final String API_INFO_CONTACT_EMAIL = "info@altech.com";
    private static final Contact DEFAULT_CONTACT = new Contact(API_INFO_CONTACT_NAME, API_INFO_CONTACT_URL, API_INFO_CONTACT_EMAIL);
    private static final String API_INFO_TITLE = "Demo Api";
    private static final String API_INFO_VERSION = "1.0.0";
    private static final String APPLICATION_JSON = "application/json";
    private static final String APPLICATION_XML = "application/xml";

    private ApiInfo apiInfo = new ApiInfoBuilder()
            .contact(DEFAULT_CONTACT)
            .description(API_INFO_DESCRIPTION)
            .license(API_INFO_LICENSE)
            .licenseUrl(API_INFO_LICENSE_URL)
            .title(API_INFO_TITLE)
            .version(API_INFO_VERSION)
            .build();

    private Set<String> defaultProducesAndConsumes =
            new HashSet<>(Arrays.asList(APPLICATION_JSON, APPLICATION_XML));

    // Bean - Docket
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .produces(defaultProducesAndConsumes)
                .consumes(defaultProducesAndConsumes);
    }
    // Swagger 2
    // All the paths
    // All the apis


}
