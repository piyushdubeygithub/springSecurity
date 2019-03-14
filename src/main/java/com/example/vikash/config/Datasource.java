package com.example.vikash.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class Datasource {
	@Autowired
	EnvConfiguration configuration;


	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(configuration.getDBDriver());
		dataSource.setUrl("jdbc:mysql://" + configuration.getDBIp() + ":" + configuration.getDBPort() + "/"
				+ configuration.getDBName());
		dataSource.setUsername(configuration.getDBUser());
		dataSource.setPassword(configuration.getDBPass().trim());
		return dataSource;
	}

}
