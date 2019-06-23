package com.prosmv.form;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prosmv.domain.Factory;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserForm {
	@NotEmpty(message="Please enter valid username")
	@NotNull(message="Please enter valid username")
	private String username;
	private String name;
	private String password;
	@NotEmpty(message="Please enter valid email")
	@NotNull(message="Please enter valid email")
	@Email
	private String email;
	private boolean isActive;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String roleName;
	private List<String> factoryNames;
	private String companyName;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public List<String> getFactoryNames() {
		return factoryNames;
	}

	public void setFactoryNames(List<String> factoryNames) {
		this.factoryNames = factoryNames;
	}



}
