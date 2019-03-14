package com.example.vikash.config;

import org.springframework.beans.factory.annotation.Value;



public class DevelopmentEnv implements EnvConfiguration {
	@Value("${vikash.db.name.dev}")
	private String dbName;
	@Value("${vikash.db.driver}")
	private String dbDriverName;
	@Value("${vikash.db.port.dev}")
	private String dbPort;
	@Value("${vikash.db.ip.dev}")
	private String dbIpName;
	@Value("${vikash.db.auth.dev.user}")
	private String dbUserName;
	@Value("${vikash.db.auth.dev.pass}")
	private String dbPassword;
	@Override
	public String getDBName() {
		// TODO Auto-generated method stub
		return dbName;
	}
	@Override
	public String getDBDriver() {
		// TODO Auto-generated method stub
		return dbDriverName;
	}
	@Override
	public String getDBPort() {
		// TODO Auto-generated method stub
		return dbPort;
	}
	@Override
	public String getDBIp() {
		// TODO Auto-generated method stub
		return dbIpName;
	}
	@Override
	public String getDBUser() {
		// TODO Auto-generated method stub
		return dbUserName;
	}
	@Override
	public String getDBPass() {
		// TODO Auto-generated method stub
		return dbPassword;
	}
	
}
