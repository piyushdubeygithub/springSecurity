package com.example.vikash.config;

import org.springframework.beans.factory.annotation.Value;


public class ProductionEnv implements EnvConfiguration {

	@Value("${vikash.db.name.pro}")
	public String dbName;
	@Value("${vikash.db.driver}")
	public String dbDriverName;
	@Value("${vikash.db.port.pro}")
	public String dbPort;
	@Value("${vikash.db.ip.pro}")
	public String dbIpName;
	@Value("${vikash.db.auth.pro.user}")
	public String dbUserName;
	@Value("${vikash.db.auth.pro.pass}")
	public String dbPassword;
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
