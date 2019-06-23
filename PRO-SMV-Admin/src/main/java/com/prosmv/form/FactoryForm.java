package com.prosmv.form;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FactoryForm {
	@NotEmpty(message="Please enter valid name")
	@NotNull(message="Please enter valid name")
	private String name;
	@NotEmpty(message="Please enter valid mobileNumber")
	@NotNull (message="Please enter valid mobileNumber")
	private String mobileNumber;
	private String address;
	@NotEmpty (message="Please enter valid email")
	@NotNull (message="Please enter valid email")
	@Email(message="Please enter valid email")
	private String email;
	private String membership;
	private Timestamp licenseExpDate;
	private Timestamp licenseIssueDate;
	private String companyName;
	private boolean isActive;

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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


}
