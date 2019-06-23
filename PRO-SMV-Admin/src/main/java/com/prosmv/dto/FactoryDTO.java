package com.prosmv.dto;

import java.sql.Timestamp;

import com.prosmv.domain.Company;
import com.prosmv.domain.Factory;

public class FactoryDTO {
	
	private Long   id;
	private String name;
	private String mobileNumber;
	private String address;
	private String email;
	private String membership;
	private Timestamp licenseExpDate;
	private Timestamp licenseIssueDate;
	private	CompanyDTO company;
	
	public FactoryDTO(Factory factory) {
		if(factory != null) {
			this.id = factory.getId();
			this.name = factory.getName();
			this.mobileNumber = factory.getMobileNumber();
			this.address = factory.getAddress();
			this.email = factory.getEmail();
			this.membership = factory.getMembership();
			this.licenseExpDate = factory.getLicenseExpDate();
			this.licenseIssueDate = factory.getLicenseIssueDate();
			if(factory.getCompany() != null) {
				company = new CompanyDTO(factory.getCompany());
			}
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
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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
	public String getMembership() {
		return membership;
	}
	public void setMembership(String membership) {
		this.membership = membership;
	}
	public Timestamp getLicenseExpDate() {
		return licenseExpDate;
	}
	public void setLicenseExpDate(Timestamp licenseExpDate) {
		this.licenseExpDate = licenseExpDate;
	}
	public Timestamp getLicenseIssueDate() {
		return licenseIssueDate;
	}
	public void setLicenseIssueDate(Timestamp licenseIssueDate) {
		this.licenseIssueDate = licenseIssueDate;
	}

	public CompanyDTO getCompany() {
		return company;
	}

	public void setCompany(CompanyDTO company) {
		this.company = company;
	}
	
	
}
