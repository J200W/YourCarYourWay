package com.example.ycyw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
public class YcywApplication {

	public static void main(String[] args) {
		SpringApplication.run(YcywApplication.class, args);
	}

}
