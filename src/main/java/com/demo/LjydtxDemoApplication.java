package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.demo.mapper")
@SpringBootApplication//(scanBasePackages = {"com.demo"})
@ComponentScan(basePackages = {"com.demo"})
public class LjydtxDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LjydtxDemoApplication.class, args);
	}
}
