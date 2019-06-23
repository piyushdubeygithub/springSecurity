package com.prosmv.form;

import java.sql.Timestamp;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CompanyForm {

	@NotEmpty(message="Please enter valid name")
	@NotNull(message="Please enter valid name")
	private String name;
	private String landLineNumber;
	@NotEmpty(message="Please enter valid mobileNumber")
	@NotNull (message="Please enter valid mobileNumber")
	private String mobileNumber;
	private String gstNumber;
	private String address;
	@NotEmpty (message="Please enter valid email")
	@NotNull (message="Please enter valid email")
	@Email(message="Please enter valid email")
	private String email;
	@NotEmpty(message="Please use other password")
	@NotNull(message="Please use other password")
	private String password;
	private String companyHead;
	@NotEmpty(message="Please use  username")
	@NotNull(message="Please use  username")
	private String userName;
	private Timestamp lastPay;

	public Timestamp getLastPay() {
		return lastPay;
	}

	public void setLastPay(Timestamp lastPay) {
		this.lastPay = lastPay;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompanyHead() {
		return companyHead;
	}

	public void setCompanyHead(String companyHead) {
		this.companyHead = companyHead;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


}
