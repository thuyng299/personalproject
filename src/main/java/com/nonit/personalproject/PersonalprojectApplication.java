package com.nonit.personalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PersonalprojectApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PersonalprojectApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(PersonalprojectApplication.class, args);
	}
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name") String name) {
		return String.format("Hello %s!", name);
	}
}
