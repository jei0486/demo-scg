package com.demo.scg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;


@SpringBootApplication
//@EnableWebFluxSecurity
public class DemoScgApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoScgApplication.class, args);
	}

}
