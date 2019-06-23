package com.prosmv.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prosmv.domain.Company;
import com.prosmv.domain.Factory;
import com.prosmv.dto.FactoryDTO;
import com.prosmv.dto.FactoryListDTO;
import com.prosmv.form.FactoryForm;
import com.prosmv.repositories.CompanyRepository;
import com.prosmv.repositories.FactoryRepository;

@Service
public class FactoryService {

	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	FactoryRepository factoryRepository;

	public String saveFactory(FactoryForm factoryForm) {
        String response = null;
		if (!isFactoryExists(factoryForm)) {
			Factory factory = getFactory(factoryForm);
			if (factory == null) {
				response =  "company does not exists";
			}
			factory.setActive(true);
			factory = factoryRepository.save(factory);
			if (factory != null) {
				response =  "factory created successfully";
			} else {
				response = null;
			}
		} else {
			response =  "factory already exists";
		}
		return response;
	}

	public boolean isFactoryExists(FactoryForm factoryform) {
		boolean factoryExists = false;
		Company company = companyRepository.findByname(factoryform.getCompanyName());
		Factory oldFactory = factoryRepository.findByNameAndCompany(factoryform.getName(), company);
		if (oldFactory != null) {
			factoryExists = true;
		}
		return factoryExists;
	}

	private Factory getFactory(FactoryForm factoryForm) {
		Factory factory = new Factory();
		Company company = companyRepository.findByname(factoryForm.getCompanyName());
		if (company != null) {
			factory.setCompany(company);
		} else {
			return null;
		}
		factory.setActive(true);
		factory.setAddress(factoryForm.getAddress());
		factory.setEmail(factoryForm.getEmail());
		factory.setMembership(factoryForm.getMembership());
		factory.setMobileNumber(factoryForm.getMobileNumber());
		factory.setLicenseIssueDate(factoryForm.getLicenseIssueDate());
		factory.setLicenseExpDate(factoryForm.getLicenseExpDate());
		factory.setName(factoryForm.getName());
		return factory;
	}

	public String updateFactory(FactoryForm factoryForm) {
        String response = null;
		Factory factory = getFactory(factoryForm);
		Factory oldFactory = factoryRepository.findByNameAndCompany(factoryForm.getName(), factory.getCompany());
		if (oldFactory != null) {
			factory.setId(oldFactory.getId());
			factoryRepository.save(factory);
			response = "factory updated successfully";
		} else {
			response = "factory not found";
		}
		return response;
	}

	public FactoryListDTO getFactoryList() {
		List<FactoryDTO> factoryList = new ArrayList<>();
		List<Factory> factories = factoryRepository.findAll();
		for (Factory factory : factories) {
			if (!factory.isActive()) {
				continue;
			}
			FactoryDTO factoryDTO = new FactoryDTO(factory);
			factoryList.add(factoryDTO);
		}
		return new FactoryListDTO(factoryList);
	}

	public String deActivateFactory(String factoryName) {
		String response = null;
		Factory factory = factoryRepository.findByname(factoryName);
		factory.setActive(false);
		factoryRepository.save(factory);
		response =  "factory is deactivated";
		return response;
	}

	public String activateFactory(String factoryName) {
		String response = null;
		Factory factory = factoryRepository.findByname(factoryName);
		factory.setActive(true);
		factoryRepository.save(factory);
		response = "factory is activated";
		return response;
	}

}
