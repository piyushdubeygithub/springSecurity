package com.example.vikash.config;

import org.springframework.beans.factory.annotation.Value;


public class TestingEnv implements EnvConfiguration {
	@Value("${vikash.db.name.test}")
	private String dbName;
	@Value("${vikash.db.driver}")
	private String dbDriverName;
	@Value("${vikash.db.port.test}")
	private String dbPort;
	@Value("${vikash.db.ip.test}")
	private String dbIpName;
	@Value("${vikash.db.auth.test.user}")
	private String dbUserName;
	@Value("${vikash.db.auth.test.pass}")
	private String dbPassword;
	@Override
	public String getDBName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getDBDriver() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getDBPort() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getDBIp() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getDBUser() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getDBPass() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
