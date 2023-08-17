package com.rodriguez.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Scanner;

@SpringBootApplication
public class ChallengeClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeClientApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		RestTemplate restTemplate = builder.build();
		restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8080"));
		return restTemplate;
	}

	@Bean
	@Scope(value= BeanDefinition.SCOPE_SINGLETON)
	public Scanner scanner(){
		return new Scanner(System.in);
	}

}
