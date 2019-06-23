package com.prosmv.dto;

import java.io.Serializable;
import java.util.List;
public class UserListDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5626581513419737743L;

	private List<UserDTO> userListDTO;

	public UserListDTO(List<UserDTO> userListDTO) {
		this.userListDTO = userListDTO;
	}

	public UserListDTO() {
	}

	public List<UserDTO> getUserListDTO() {
		return userListDTO;
	}

	public void setUserListDTO(List<UserDTO> userListDTO) {
		this.userListDTO = userListDTO;
	}
	
}
