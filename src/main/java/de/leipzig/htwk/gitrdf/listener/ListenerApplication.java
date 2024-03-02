package de.leipzig.htwk.gitrdf.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {"de.leipzig.htwk.gitrdf.listener", "de.leipzig.htwk.gitrdf.database.common"})
@EntityScan(basePackages = "de.leipzig.htwk.gitrdf.database.common.entity")
@EnableJpaRepositories(basePackages = "de.leipzig.htwk.gitrdf.database.common.repository")
public class ListenerApplication {

	public static void main(String[] args) {

		SpringApplication.run(ListenerApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // Apply CORS settings to all endpoints
						.allowedOrigins("http://localhost:3000") // or use "*" for all origins
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}

}
