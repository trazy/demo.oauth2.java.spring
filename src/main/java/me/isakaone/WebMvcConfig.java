package me.isakaone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import me.isakaone.gms.entity.Fruit;
import me.isakaone.gms.repositories.FruitRepository;

@Configuration
@EnableWebMvc
@EnableAutoConfiguration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
		"classpath:/META-INF/resources/",
		"classpath:/META-INF/static/",
		"classpath:/META-INF/public/",
		"classpath:/WEB-INF/resources/",
		"classpath:/WEB-INF/static/",
		"classpath:/WEB-INF/public/",
		"classpath:/resources/",
		"classpath:/static/",
		"classpath:/public/"
	};
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index.html");
		registry.addViewController("/sign-in").setViewName("forward:/sign-in.html");
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(FruitRepository fruitRepository) {
		return args -> {
			Fruit apple = new Fruit();
			apple.setName("apple");
			apple.setColor("red");
			
			fruitRepository.save(apple);
		};
	}
}
