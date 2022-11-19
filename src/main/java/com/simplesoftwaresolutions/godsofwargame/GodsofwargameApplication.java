package com.simplesoftwaresolutions.godsofwargame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


//@SpringBootApplication //This replaces  the following annotations but @Enable... needs to be non defailt (for now)
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan
@Configuration
public class GodsofwargameApplication {

	public static void main(String[] args) {
		SpringApplication.run(GodsofwargameApplication.class, args);
	}

}
