package com.example.meetexApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MeetexApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetexApiApplication.class, args);
	}

}
