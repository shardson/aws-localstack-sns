package com.aws.hellosns;

import com.aws.hellosns.service.ContactPublisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSnsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSnsApplication.class, args);
	}

}
