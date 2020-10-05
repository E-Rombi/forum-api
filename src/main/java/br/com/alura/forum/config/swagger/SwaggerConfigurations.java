package br.com.alura.forum.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.alura.forum.controller.DocumentacaoController;
import br.com.alura.forum.modelo.Usuario;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {
	
	@Bean
	public Docket forumApi() {
		return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("br.com.alura.forum"))
        .paths(PathSelectors.ant("/**"))
        .build()
        .ignoredParameterTypes(Usuario.class)
        .globalOperationParameters(                
        		Arrays.asList(
                        new ParameterBuilder()
                            .name("Authorization")
                            .description("Header para Token JWT")
                            .modelRef(new ModelRef("string"))
                            .parameterType("header")
                            .required(false)
                            .build()))
        .apiInfo(metaInfo());
	}
	
	private ApiInfo metaInfo() {

		ApiInfo apiInfo = 
				new ApiInfoBuilder()
					.title("API Fórum - Cursos UNIMEP")
					.description("API construída para integração do fórum da faculdade UNIMEP com o aplicativo mobile.")
					.version("1.0")
					.contact(new Contact("Eduardo Rombi", "", "eduardo.rombi@a.unimep.br"))
					.termsOfServiceUrl("Termos de Serviço")
					.licenseUrl("Apache License Version 2.0")
					.build();

        return apiInfo;
    }

}
