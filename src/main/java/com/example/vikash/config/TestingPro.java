package com.example.vikash.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("test")

public class TestingPro {
	@Bean
	public EnvConfiguration getTestingConfig() {
		return new TestingEnv();
	}

}
