package com.smo.imagen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ImagenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImagenApplication.class, args);
	}

}
