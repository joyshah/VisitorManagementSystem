package com.example.vms.configuration;

import java.security.SecureRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MyConfiguration {

	@Bean
	public SecureRandom getSecureRandom() {

		return new SecureRandom();

	}

}
