package com.conservatory.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import java.io.IOException;

@SpringBootApplication(exclude= MongoAutoConfiguration.class)
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);

		ServerSocket sv = new ServerSocket(9000);
		try {
			sv.start();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}