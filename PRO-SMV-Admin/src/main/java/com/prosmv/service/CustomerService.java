package com.prosmv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prosmv.domain.Brand;
import com.prosmv.domain.Company;
import com.prosmv.domain.CustomerSupplier;
import com.prosmv.domain.Factory;
import com.prosmv.dto.ResponseDTO;
import com.prosmv.form.CustomerForm;
import com.prosmv.repositories.BrandRepository;
import com.prosmv.repositories.CompanyRepository;
import com.prosmv.repositories.CustomerRepository;
import com.prosmv.repositories.FactoryRepository;
import com.prosmv.util.Constant;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private FactoryRepository factoryRepository;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private BrandRepository brandRepository;

	public ResponseDTO saveCustomer(CustomerForm customerForm) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (!isCustomerExists(customerForm)) {
			CustomerSupplier customerSupplier = getCustomerSupplier(customerForm);
			if (customerSupplier == null) {
				responseDTO.setMessage("company is required");
				responseDTO.setResponseMessage(Constant.ERROR);
			} else {
				customerRepository.save(customerSupplier);
				responseDTO.setMessage("customer created successfully");
				responseDTO.setResponseMessage(Constant.SUCCESS);
			}
		} else {
			responseDTO.setMessage("customer already exists");
			responseDTO.setResponseMessage(Constant.ERROR);
		}
		return responseDTO;
	}

	private CustomerSupplier getCustomerSupplier(CustomerForm customerForm) {
		CustomerSupplier customerSupplier = new CustomerSupplier();
		customerSupplier.setName(customerForm.getCustomername());
		Company company = null;
		if (customerForm.getCompanyName() != null) {
			company = companyRepository.findByname(customerForm.getCompanyName());
			if (company != null) {
				customerSupplier.setCompany(company);
			}
		}
		Factory factory = factoryRepository.findByname(customerForm.getFactoryName());
		if (factory != null) {
			customerSupplier.setFactory(factory);
			if (company == null) {
				company = factory.getCompany();
				customerSupplier.setCompany(company);
			}
		}
		if (company == null) {
			return null;
		}
		if (customerForm.getAddress() != null) {
			customerSupplier.setAddress(customerForm.getAddress());
		}
		if (customerForm.getCustomerType() != null) {
			customerSupplier.setCustomerType(customerForm.getCustomerType());
		}
		if (customerForm.getEmail() != null) {
			customerSupplier.setEmail(customerForm.getEmail());
		}
		if (customerForm.getMobileNumber() != null) {
			customerSupplier.setMobileNumber(customerForm.getMobileNumber());
		}
        customerSupplier.setActive(true);
        customerSupplier.setDeleted(false);
		return customerSupplier;
	}

	private boolean isCustomerExists(CustomerForm customerForm) {
		boolean customerExists = false;
		CustomerSupplier customerSupplier = customerRepository.findByName(customerForm.getCustomername());
		if (customerSupplier != null) {
			customerExists = true;
		}
		return customerExists;
	}

	public ResponseDTO updateCustomer(CustomerForm customerForm) {
		ResponseDTO responseDTO = new ResponseDTO();
		CustomerSupplier oldCustomer = customerRepository.findByName(customerForm.getCustomername());
		if (oldCustomer != null) {
			CustomerSupplier customer = getCustomerSupplier(customerForm);
			if (customer != null) {
				customer.setId(oldCustomer.getId());
				customerRepository.save(customer);
				responseDTO.setMessage("customer is updated");
				responseDTO.setResponseMessage(Constant.SUCCESS);
			} else {
				responseDTO.setMessage("company is required");
				responseDTO.setResponseMessage(Constant.ERROR);
			}
		} else {
			responseDTO.setMessage("customer not found");
			responseDTO.setResponseMessage(Constant.ERROR);
		}
		return responseDTO;
	}

	public ResponseDTO addBrand(String customerName, String brandName) {
		ResponseDTO responseDTO = new ResponseDTO();
		Brand brand = brandRepository.findByName(brandName);
		if (brand != null) {
			responseDTO.setMessage("this brand already exists");
			responseDTO.setResponseMessage(Constant.SUCCESS);
		} else {
			brand = brandRepository.save(brand);
			CustomerSupplier customerSupplier = customerRepository.findByName(customerName);
			List<Brand> brands = customerSupplier.getBrands();
			brands.add(brand);
            customerSupplier.setBrands(brands);
            customerRepository.save(customerSupplier);
		}
		return responseDTO;
	}

	public ResponseDTO deActivateCustomer(String customerName) {
		CustomerSupplier customerSupplier = customerRepository.findByName(customerName);
		customerSupplier.setActive(false);
		customerRepository.save(customerSupplier);
		return new ResponseDTO(Constant.SUCCESS, "customer is deActivated");
	}

	public ResponseDTO activateCustomer(String customerName) {
		CustomerSupplier customerSupplier = customerRepository.findByName(customerName);
		customerSupplier.setActive(true);
		customerRepository.save(customerSupplier);
		return new ResponseDTO(Constant.SUCCESS, "customer is activated");		
	}

	public ResponseDTO getAllBrands(String customerName) {
		ResponseDTO responseDTO = new ResponseDTO();
		CustomerSupplier customerSupplier = customerRepository.findByName(customerName);
		if(customerSupplier != null) {
			List<Brand> brands = customerSupplier.getBrands();
			List<Brand> validBrands = customerSupplier.getBrands();
			for(Brand brand : brands) {
				if(!brand.isDeleted()) {
					validBrands.add(brand);
				}
			}
			responseDTO.setResponseObject(validBrands);
		}else {
			responseDTO.setResponseMessage("no brand was found");
		}
		responseDTO.setResponseMessage(Constant.SUCCESS);
		return responseDTO;
	}

	public ResponseDTO deleteBrand(String brandName) {
		Brand brand = brandRepository.findByName(brandName);
		brand.setDeleted(true);
		brandRepository.save(brand);
		return new ResponseDTO(Constant.SUCCESS, "brand is deleted");		
	}

}
