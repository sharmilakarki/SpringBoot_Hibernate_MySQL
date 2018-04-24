package com.sharmila;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@ComponentScan(basePackages={"com.sharmila.controller","com.sharmila.manager","com.sharmila.repository","com.sharmila.domain"})
@SpringBootApplication
public class ProjectMApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjectMApplication.class, args);
	}
}
