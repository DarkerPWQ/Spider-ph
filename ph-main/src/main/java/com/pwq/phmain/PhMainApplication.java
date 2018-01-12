package com.pwq.phmain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = "com.pwq.phmain")
@SpringBootApplication
public class PhMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhMainApplication.class, args);
	}



}
