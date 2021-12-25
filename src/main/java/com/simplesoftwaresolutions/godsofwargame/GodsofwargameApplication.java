package com.simplesoftwaresolutions.godsofwargame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class GodsofwargameApplication {

	public static void main(String[] args) {
		SpringApplication.run(GodsofwargameApplication.class, args);
	}
//This is a test
}
