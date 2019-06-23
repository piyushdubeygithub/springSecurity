package com.prosmv.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyListDTO {

	private List<CompanyDTO> companyListDTO;

	public CompanyListDTO() {

	}

	public CompanyListDTO(List<CompanyDTO> companyListDTO) {
		this.companyListDTO = companyListDTO;
	}

	public List<CompanyDTO> getCompanyList() {
		return companyListDTO;
	}

	public void setCompanyList(List<CompanyDTO> companyListDTO) {
		this.companyListDTO = companyListDTO;
	}

}
