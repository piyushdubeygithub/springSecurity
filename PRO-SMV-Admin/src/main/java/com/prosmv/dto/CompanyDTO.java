package com.prosmv.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.prosmv.domain.Company;
import com.prosmv.domain.User;
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyDTO {
	
	private Long id;
	private String name;
	private String landLineNumber;
	private String mobileNumber;
	private String gstNumber;
	private String address;
	private String email;
	private Timestamp lastPay;
	private UserDTO userDTO;
	private boolean isActive;
	private Timestamp startDate;
	private Timestamp endDate;
	
	public CompanyDTO() {
		
	}
	
	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public CompanyDTO(Company company){
		if(company!= null) {
			if(company.getId() != null) {
				this.id = company.getId();
			}
			if(company.getName() != null) {
				this.name = company.getName();	
			}
			if(company.getLandLineNumber() != null) {
				this.landLineNumber = company.getLandLineNumber();
			}
			if(company.getMobileNumber() != null) {
				this.mobileNumber = company.getMobileNumber();
			}
			if(company.getGstNumber() != null) {
				this.gstNumber = company.getGstNumber();
			}
			if(company.getAddress() != null) {
				this.address = company.getAddress();
			}
			if(company.getEmail() != null) {
				this.email = company.getEmail();
			}
			if(company.getLastPay() != null){
				this.lastPay = company.getLastPay();
			}
			if(company.getUser() != null) {
				this.userDTO =new UserDTO(company.getUser());
			}
			if(company.getStartDate() != null) {
				this.startDate = company.getStartDate();
			}
			this.isActive = company.isActive();
		}
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLandLineNumber() {
		return landLineNumber;
	}
	public void setLandLineNumber(String landLineNumber) {
		this.landLineNumber = landLineNumber;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getGstNumber() {
		return gstNumber;
	}
	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getLastPay() {
		return lastPay;
	}
	public void setLastPay(Timestamp lastPay) {
		this.lastPay = lastPay;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

}
