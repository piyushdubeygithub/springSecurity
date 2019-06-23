package com.prosmv.dto;

import java.util.List;

public class FactoryListDTO {

	List<FactoryDTO> factoryListDTO;

	public FactoryListDTO() {

	}

	public FactoryListDTO(List<FactoryDTO> factoryListDTO) {
      this.factoryListDTO = factoryListDTO;
	}

	public List<FactoryDTO> getFactoryListDTO() {
		return factoryListDTO;
	}

	public void setFactoryListDTO(List<FactoryDTO> factoryListDTO) {
		this.factoryListDTO = factoryListDTO;
	}

}
