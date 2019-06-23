package com.prosmv.form;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MachineForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2386170580868489525L;

	private Long   id;
	@NotNull(message="Please enter valid name")
	@NotEmpty(message="Please enter valid name")
	private String machineName;
	private int rpm;
	private int allowance;
	private String machineType;
	private String machineColor;
	@NotNull(message="Please enter factory name")
	@NotEmpty(message="Please enter factory name")
	private String factoryName;
	@NotNull(message="Please enter stitchClass name")
	@NotEmpty(message="Please enter stitchClass name")
	private String stitchClassName;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public int getRpm() {
		return rpm;
	}
	public void setRpm(int rpm) {
		this.rpm = rpm;
	}
	public int getAllowance() {
		return allowance;
	}
	public void setAllowance(int allowance) {
		this.allowance = allowance;
	}
	public String getMachineType() {
		return machineType;
	}
	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}
	public String getMachineColor() {
		return machineColor;
	}
	public void setMachineColor(String machineColor) {
		this.machineColor = machineColor;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public String getStitchClassName() {
		return stitchClassName;
	}
	public void setStitchClassName(String stitchClassName) {
		this.stitchClassName = stitchClassName;
	}
}
