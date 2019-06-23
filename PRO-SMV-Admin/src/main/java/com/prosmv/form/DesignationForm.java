package com.prosmv.form;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class DesignationForm {
	
	@NotEmpty (message="designationname must not be empty")
	@NotNull(message="designationname must not be null")
	private String designationname;
	@NotEmpty(message="personelAllowance must not be null")
	@NotNull(message="personelAllowance must not be null")
	private int personelAllowance;
	@NotEmpty(message="contigencyAllowance must not be null")
	@NotNull(message="contigencyAllowance must not be null")
	private int contigencyAllowance;
	@NotEmpty (message="company must not be empty")
	@NotNull(message="company must not be null")
	private String companyName;
	private int wagerate;
	private String loginUserName;

	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLoginUserName() {
		return loginUserName;
	}
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}
	public String getDesignationname() {
		return designationname;
	}
	public void setDesignationname(String designationname) {
		this.designationname = designationname;
	}
	public int getPersonelAllowance() {
		return personelAllowance;
	}
	public void setPersonelAllowance(int personelAllowance) {
		this.personelAllowance = personelAllowance;
	}
	public int getContigencyAllowance() {
		return contigencyAllowance;
	}
	public void setContigencyAllowance(int contigencyAllowance) {
		this.contigencyAllowance = contigencyAllowance;
	}
	public int getWagerate() {
		return wagerate;
	}
	public void setWagerate(int wagerate) {
		this.wagerate = wagerate;
	}
	
}
