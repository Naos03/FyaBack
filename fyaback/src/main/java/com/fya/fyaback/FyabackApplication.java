package com.fya.fyaback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FyabackApplication {

	public static void main(String[] args) {
		SpringApplication.run(FyabackApplication.class, args);
	}

}
