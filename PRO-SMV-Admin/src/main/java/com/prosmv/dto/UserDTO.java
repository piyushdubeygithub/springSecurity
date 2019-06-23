package com.prosmv.dto;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.prosmv.domain.Factory;
import com.prosmv.domain.Role;
import com.prosmv.domain.User;
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
	private String username;
	private String name;
	private String password;
	private String email;
	private boolean isActive;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private RoleDTO role;
	private List<Factory> factories;

	public UserDTO(User user) {
		if (user != null) {
			if(user.getUsername() != null) {
				this.username = user.getUsername();
			}
			if(user.getName() != null) {
				this.name = user.getName();
			}
			if(user.getEmail() != null) {
				this.email = user.getEmail();
			}
			if(user.getCreatedAt() != null) {
				this.createdAt = user.getCreatedAt();
			}
			if(user.getUpdatedAt() != null) {
				this.updatedAt = user.getUpdatedAt();
			}
			this.isActive = user.isActive();
			if (user.getRole() != null && user.getRole().getRoleName() != null) {
				this.role = new RoleDTO(user.getRole().getRoleName());
			}
			// this.factories = user.getFactories();
		}
	}

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

	public List<Factory> getFactories() {
		return factories;
	}

	public void setFactories(List<Factory> factories) {
		this.factories = factories;
	}

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}

}
