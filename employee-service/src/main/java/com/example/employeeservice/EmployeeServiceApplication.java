package com.example.employeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@SpringBootApplication
//@EnableAsync
//@EnableEurekaClient
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
		System.out.println("Employee microservice started !");
	}
	
	   @Bean
	    public WebClient webClient(){
	        return WebClient.builder().build();
	    }
	   
//	@Bean
//	public RestTemplate restTemplate()
//	{
//		return new RestTemplate();
//	}

}
