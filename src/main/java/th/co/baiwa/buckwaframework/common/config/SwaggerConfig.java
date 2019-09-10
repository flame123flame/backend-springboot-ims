package th.co.baiwa.buckwaframework.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Lists;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.scanners.ApiDescriptionReader;
import springfox.documentation.spring.web.scanners.ApiListingScanner;
import springfox.documentation.spring.web.scanners.ApiModelReader;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import th.co.baiwa.buckwaframework.security.rest.documentation.FormLoginOperations;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Value("${application.swagger.enable:true}")
	private boolean externallyConfiguredFlag;
	
	@Bean
	public Docket customImplementation(){
		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(getApiInfo())
			.enable(externallyConfiguredFlag)
			.select()
			.paths(PathSelectors.ant("/api/**"))
			.build();
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo("REST Api Documentation",
			"REST Api Documentation Description",
			"1.0",
			"",
			new Contact("", "", ""),
			"", "",
			Lists.newArrayList());
	}
	
	@Primary
	@Bean
	public ApiListingScanner addExtraOperations(
			ApiDescriptionReader apiDescriptionReader,
			ApiModelReader apiModelReader,
			DocumentationPluginsManager pluginsManager,
			TypeResolver typeResolver) {
		return new FormLoginOperations(apiDescriptionReader, apiModelReader, pluginsManager, typeResolver);
	}
	
}
