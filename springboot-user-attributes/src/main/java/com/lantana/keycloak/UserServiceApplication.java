package com.lantana.keycloak;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

	private static Log logger = LogFactory.getLog(UserServiceApplication.class);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(UserServiceApplication.class, args);
	}
             
        

}
