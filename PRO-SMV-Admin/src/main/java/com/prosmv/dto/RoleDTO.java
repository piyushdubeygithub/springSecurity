package com.prosmv.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.prosmv.domain.Role;
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDTO {
	private Long id;
	private String roleName;
	private UserDTO createdBy;
	private UserDTO updatedBy;
	private CompanyDTO company;
	
	public RoleDTO(String roleName) {
		this.roleName = roleName;
	}
	
	public RoleDTO(Role role) {
		if(role != null) {
			this.id = role.getId();
			this.roleName = role.getRoleName();
			if(role.getCreatedBy() != null) {
				createdBy = new UserDTO(role.getCreatedBy());
			}
			if(role.getUpdatedBy() != null) {
				updatedBy = new UserDTO(role.getUpdatedBy());
			}
			if(role.getCompany() != null) {
				company = new CompanyDTO(role.getCompany());
			}
		}
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public UserDTO getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserDTO createdBy) {
		this.createdBy = createdBy;
	}
	public UserDTO getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(UserDTO updatedBy) {
		this.updatedBy = updatedBy;
	}
	public CompanyDTO getCompany() {
		return company;
	}
	public void setCompany(CompanyDTO company) {
		this.company = company;
	}
	
	
}
