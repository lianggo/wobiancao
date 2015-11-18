package com.wobiancao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.wobiancao.config.AdminProperties;

@SpringBootApplication
@EnableConfigurationProperties({ AdminProperties.class })
public class WobiancaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WobiancaoApplication.class, args);
	}
}
