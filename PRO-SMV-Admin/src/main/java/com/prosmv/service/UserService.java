package com.prosmv.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.prosmv.domain.Company;
import com.prosmv.domain.Factory;
import com.prosmv.domain.Role;
import com.prosmv.domain.User;
import com.prosmv.dto.UserDTO;
import com.prosmv.dto.UserListDTO;
import com.prosmv.form.UserForm;
import com.prosmv.repositories.CompanyRepository;
import com.prosmv.repositories.FactoryRepository;
import com.prosmv.repositories.RoleRepository;
import com.prosmv.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	FactoryRepository factoryRepository;
	

	public String saveUser(UserForm userForm) {
        String response = null;
		if (!isUserExists(userForm)) {
			User user = getUser(userForm);
			if (user == null) {
				response = "company does not exists";
				return response;
			}
			Timestamp ts = new Timestamp(new Date().getTime());
			user.setCreatedAt(ts);
			user.setActive(true);
			userRepository.save(user);
			response =  "user created successfully";
		} else {
			response =  "user already exists";
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	private User getUser(UserForm userForm) {
		User user = new User();
		Company company = null;
		company = companyRepository.findByname(userForm.getCompanyName());
		List<Factory> factories = new ArrayList<>();
		if (userForm.getFactoryNames() != null) {
			List<String> factoryNames = userForm.getFactoryNames();
			if (!factoryNames.isEmpty() && company == null) {
				Factory factory = factoryRepository.findByname(factoryNames.get(0));
				if (factory != null) {
					company = factory.getCompany();
				}
			}
			if (company == null) {
				return null;
			}else {
				user.setCompany(company);
			}
			for (String factoryName : factoryNames) {
				Factory factory = factoryRepository.findByname(factoryName);
				factories.add(factory);
			}
			user.setFactories(factories);
		}
		user.setUsername(userForm.getUsername());
		user.setActive(userForm.isActive());
		user.setCreatedAt(userForm.getCreatedAt());
		user.setEmail(userForm.getEmail());
		user.setName(userForm.getName());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(userForm.getPassword());
		user.setPassword(hashedPassword);
		user.setUpdatedAt(userForm.getUpdatedAt());
		Role role = roleRepository.findByRoleNameAndCompany(userForm.getRoleName(), company);
		user.setRole(role);
		return user;
	}

	public boolean isUserExists(UserForm userForm) {
		boolean userExists = false;
		User oldUser = userRepository.findByUsername(userForm.getUsername());
		if (oldUser != null) {
			userExists = true;
		}
		return userExists;
	}

	public String updateUser(@Valid UserForm userForm) {
		String response = null;
		User oldUser = userRepository.findByUsername(userForm.getUsername());
		if (oldUser != null) {
			User user = getUser(userForm);
			user.setId(oldUser.getId());
			userRepository.save(user);
			response = "user updated";
		} else {
			response = "user not found";
		}
		return response;
	}

	public UserListDTO getUserList(String companyName) {
		List<UserDTO> userList = new ArrayList<>();
		Company company = companyRepository.findByname(companyName);
		List<User> users = userRepository.findByCompany(company);
		for (User user : users) {
			if (!user.isActive()) {
				continue;
			}
			UserDTO userDTO = new UserDTO(user);
			userList.add(userDTO);
		}
		return new UserListDTO(userList);
	}

	public String deActivateUser(String username) {
		String response = null;
		User user = userRepository.findByUsername(username);
		if (user != null) {
			user.setActive(false);
			userRepository.save(user);
			response = "user is deactivated";
		} else {
			response = "user is not found";
		}
		return response;
	}

	public String activateUser(String username) {
		String response = null;
		User user = userRepository.findByUsername(username);
		if (user != null) {
			user.setActive(true);
			userRepository.save(user);
			response =  "user is activated";
		} else {
			response = "user is not found";
		}
		return response;
	}
}
