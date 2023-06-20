package com.tingeso.calidadservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CalidadServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalidadServiceApplication.class, args);
	}

}
