package th.co.baiwa.buckwaframework.common.config;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import th.co.baiwa.buckwaframework.common.rest.adapter.LocalDateJsonDeserializer;
import th.co.baiwa.buckwaframework.common.rest.adapter.LocalDateJsonSerializer;
import th.co.baiwa.buckwaframework.common.rest.adapter.LocalDateTimeJsonSerializer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Configure from WebMvcAutoConfiguration.addResourceHandlers()
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/**").addResourceLocations(new String[] {
			"classpath:/META-INF/resources/",
			"classpath:/resources/",
			"classpath:/static/",
			"classpath:/public/",
			"/"
		});
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding(StandardCharsets.UTF_8.name());
		
		return resolver;
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		
		GsonHttpMessageConverter gsonConverter = new GsonHttpMessageConverter();
		gsonConverter.setGson(gson());
		
		converters.add(gsonConverter);
	}
	
	@Bean(name = "gson")
	public Gson gson() {
		return new GsonBuilder()
			.serializeNulls()
			.registerTypeAdapter(LocalDate.class, LocalDateJsonSerializer.getInstance())
			.registerTypeAdapter(LocalDate.class, LocalDateJsonDeserializer.getInstance())
			.registerTypeAdapter(LocalDateTime.class, LocalDateTimeJsonSerializer.getInstance())
			.create();
	}

}
