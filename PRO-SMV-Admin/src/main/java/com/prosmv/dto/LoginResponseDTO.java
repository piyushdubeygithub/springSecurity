package com.prosmv.dto;

import java.io.Serializable;

import com.prosmv.domain.User;

public class LoginResponseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7951940370038914177L;

	public LoginResponseDTO() {
	}
	public LoginResponseDTO(String message) {
		this.message = message;
	}
	public LoginResponseDTO(String token, User user, String message) {
		this.token = token;
		this.user = user;
		this.message = message;
	}
	
	private String token;
	private User user;
	private String message;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
