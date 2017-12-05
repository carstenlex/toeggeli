package com.toeggeli.toeggeli;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.websocket.reactive.WebSocketReactiveAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
@SpringBootApplication
@EnableReactiveMongoRepositories
public class ToeggeliApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToeggeliApplication.class, args);
	}


	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper om = new ObjectMapper();
		om.findAndRegisterModules();
		return om;
	}

}
