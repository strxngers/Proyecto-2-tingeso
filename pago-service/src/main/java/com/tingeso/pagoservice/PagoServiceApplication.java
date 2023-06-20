package com.tingeso.pagoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PagoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagoServiceApplication.class, args);
	}

}
