package com.mainiway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.mainiway.dao")
public class StartUpApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartUpApplication.class, args);
	}
}
