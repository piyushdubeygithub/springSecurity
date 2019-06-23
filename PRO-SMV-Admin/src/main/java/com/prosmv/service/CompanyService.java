package com.prosmv.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.prosmv.domain.Company;
import com.prosmv.domain.Factory;
import com.prosmv.domain.Role;
import com.prosmv.domain.SubModule;
import com.prosmv.domain.SubModulePermission;
import com.prosmv.domain.User;
import com.prosmv.dto.CompanyDTO;
import com.prosmv.dto.CompanyListDTO;
import com.prosmv.dto.ResponseDTO;
import com.prosmv.form.CompanyForm;
import com.prosmv.repositories.CompanyRepository;
import com.prosmv.repositories.FactoryRepository;
import com.prosmv.repositories.RoleRepository;
import com.prosmv.repositories.SubModulePermissionRepository;
import com.prosmv.repositories.UserRepository;
import com.prosmv.util.Constant;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private FactoryService factoryService;
	@Autowired
	private FactoryRepository factoryRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private SubModulePermissionRepository subModulePermissionRepository;

	public String saveCompany(CompanyForm companyForm) {
		String response = null;
		if (isExists(companyForm)) {
			response = "user or comapny already exists";
			return response;
		}
		User user = saveUser(companyForm);
		Factory factory = saveFactory(companyForm);
		if (user == null || factory == null) {
			response = "error occured while creating user or factory";
			return response;
		}
		if (!isCompanyExists(companyForm)) {
			Company company = getCompany(companyForm, user);
			Timestamp ts = new Timestamp(new Date().getTime());
			company.setStartDate(ts);
			company.setActive(true);
			company = companyRepository.save(company);
			if (company != null) {
				createRoles(companyForm.getUserName(), company);
				response = "company created successfully";
			} else {
				response = "error occured while creating company";
			}
		} else {
			response = "company already exists";
		}
		return response;
	}

	private boolean isExists(CompanyForm companyForm) {
		boolean isExists = false;
		User user = userRepository.findByUsername(companyForm.getUserName());
		if (user != null) {
			isExists = true;
		}
		Company company = companyRepository.findByname(companyForm.getName());
		if (company != null) {
			isExists = true;
		}
		return isExists;
	}

	private void createRoles(String loginUserName, Company company) {
		User user = userRepository.findByUsername(loginUserName);
		createSARole(user, company);
		createAdminRole(user, company);
		createUserRole(user, company);
	}

	private void createSARole(User user, Company company) {
		Role role = new Role();
		role.setCreatedBy(user);
		role.setRoleName("SuperAdmin");
		role.setCompany(company);
		roleRepository.save(role);
	}

	private void createUserRole(User user, Company company) {
		Role role = new Role();
		role.setCreatedBy(user);
		role.setRoleName("User");
		role.setCompany(company);
		roleRepository.save(role);
	}

	private void createAdminRole(User user, Company company) {
		Role role = new Role();
		role.setCreatedBy(user);
		role.setRoleName("Admin");
		role.setCompany(company);
		role = roleRepository.save(role);
		user.setRole(role);
		user.setUserType("ADMIN");
		userRepository.save(user);
	}

	private Factory saveFactory(CompanyForm companyForm) {
		Factory factory = getFactory(companyForm);
		factoryRepository.save(factory);
		return factory;
	}

	private void setSubModulePermission(Company company, Role role, SubModule subModule) {
		SubModulePermission subModulePermission = new SubModulePermission();
		subModulePermission.setCanAccess(true);
		subModulePermission.setCanApprove(true);
		subModulePermission.setCanDelete(true);
		subModulePermission.setCanPrint(true);
		subModulePermission.setCanSendForApproval(true);
		subModulePermission.setCanUpdate(true);
		subModulePermission.setCanView(true);
		subModulePermission.setRole(role);
		subModulePermission.setSubModule(subModule);
		subModulePermissionRepository.save(subModulePermission);
	}

	private Company getCompany(CompanyForm companyForm, User user) {
		Company company = new Company();
		company.setActive(true);
		company.setAddress(companyForm.getAddress());
		company.setEmail(companyForm.getEmail());
		company.setGstNumber(companyForm.getGstNumber());
		company.setLandLineNumber(companyForm.getLandLineNumber());
		company.setMobileNumber(companyForm.getMobileNumber());
		company.setName(companyForm.getName());
		company.setLastPay(companyForm.getLastPay());
		company.setUser(user);
		return company;
	}

	private Factory getFactory(CompanyForm companyForm) {
		Factory factory = new Factory();
		factory.setActive(true);
		factory.setAddress(companyForm.getAddress());
		factory.setEmail(companyForm.getEmail());
		factory.setMobileNumber(companyForm.getMobileNumber());
		factory.setName(companyForm.getName());
		return factory;
	}

	public User saveUser(CompanyForm companyForm) {
		User user = getUser(companyForm);
		Timestamp ts = new Timestamp(new Date().getTime());
		user.setCreatedAt(ts);
		user.setActive(true);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		userRepository.save(user);
		user.setUserType("ADMIN");
		return user;
	}

	private User getUser(CompanyForm companyForm) {
		User user = new User();
		user.setUsername(companyForm.getUserName());
		user.setActive(true);
		user.setEmail(companyForm.getEmail());
		user.setName(companyForm.getCompanyHead());
		user.setPassword(companyForm.getPassword());
		return user;
	}

	public boolean isCompanyExists(CompanyForm companyForm) {
		boolean companyExists = false;
		Company oldCompany = companyRepository.findByname(companyForm.getName());
		if (oldCompany != null) {
			companyExists = true;
		}
		return companyExists;
	}

	public String updateCompany(CompanyForm companyForm) {
		String response = null;
		Company oldCompany = companyRepository.findByname(companyForm.getName());
		if (oldCompany != null) {
			User user = updateUser(companyForm, oldCompany);
			if (user == null) {
				response = "user not found";
			} else {
				Company company = getCompany(companyForm, user);
				company.setId(oldCompany.getId());
				company.setStartDate(oldCompany.getStartDate());
				companyRepository.save(company);
				response = "company is updated";
			}
		} else {
			response = "company not found";
		}
		return response;
	}

	private User updateUser(CompanyForm companyForm, Company company) {
		User user = null;
		User oldUser = userRepository.findByUsername(companyForm.getUserName());
		if (oldUser != null) {
			user = getUser(companyForm);
			user.setId(oldUser.getId());
			user.setCreatedAt(oldUser.getCreatedAt());
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(hashedPassword);
			user.setCompany(company);
			user = userRepository.save(user);
		}
		return user;
	}

	public CompanyListDTO getCompanyList() {
		List<CompanyDTO> companyList = new ArrayList<>();
		List<Company> companies = companyRepository.findAll();
		for (Company company : companies) {
			if (!company.isActive()) {
				continue;
			}
			CompanyDTO companyDTO = new CompanyDTO(company);
			companyList.add(companyDTO);
		}
		return new CompanyListDTO(companyList);
	}

	public String deActivateCompany(String name) {
		String response = null;
		Company company = companyRepository.findByname(name);
		company.setActive(false);
		companyRepository.save(company);
		response = "company is deActivated";
		return response;
	}

	public String activateCompany(String name) {
		String response = null;
		Company company = companyRepository.findByname(name);
		company.setActive(true);
		companyRepository.save(company);
		response = "company is activated";
		return response;
	}
}
