package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.config.PictureUploadProperties;
import com.example.starter.StartupRunner2;
import com.example.starter.StartupRunnerForBooks;
/**
 * The @EnableAutoConfiguration annotation is often placed on your main class
 *  and it implicitly defines a base “search package” for certain items.
 * @author xuminghui
 *
 */
@SpringBootApplication
@EnableConfigurationProperties({PictureUploadProperties.class}) 
@EnableScheduling
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Bean
	@Order(value = 2)
	public StartupRunnerForBooks schedulerRunner1() {
		return new StartupRunnerForBooks();
	}

	@Bean
	@Order(value = 3)
	public StartupRunner2 schedulerRunner2() { 
		return new StartupRunner2();
	}

	
}
