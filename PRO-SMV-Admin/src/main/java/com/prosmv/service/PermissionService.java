package com.prosmv.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prosmv.domain.Company;
import com.prosmv.domain.Role;
import com.prosmv.domain.SubModule;
import com.prosmv.domain.SubModulePermission;
import com.prosmv.form.PermissionForm;
import com.prosmv.repositories.CompanyRepository;
import com.prosmv.repositories.RoleRepository;
import com.prosmv.repositories.SubModulePermissionRepository;
import com.prosmv.repositories.SubModuleRepository;

@Service
public class PermissionService {

	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	private static final String RESPONSE_MESSAGE = "responseMessage";
	private static final String MESSAGE = "message";
	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	SubModuleRepository subModuleRepository;
	@Autowired
	SubModulePermissionRepository subModulePermissionRepository;

	public Map<String, Object> saveRole(PermissionForm permissionForm) {
		Map<String, Object> result = new HashMap<>();
		Role role = getRole(permissionForm);
		if (!isRoleExists(role)) {
			role = roleRepository.save(role);
			if (role != null) {
				result.put(MESSAGE, "error occured while saving role");
				result.put(RESPONSE_MESSAGE, ERROR);
				return result;
			}
		} else {
			result.put(MESSAGE, "role already exists");
			result.put(RESPONSE_MESSAGE, ERROR);
			return result;
		}
		result.put(RESPONSE_MESSAGE, SUCCESS);
		return result;
	}

	private boolean isRoleExists(Role role) {
		boolean roleExists = false;
		role = roleRepository.findByRoleNameAndCompany(role.getRoleName(), role.getCompany());
		if (role != null) {
			roleExists = true;
		}
		return roleExists;
	}

	private Role getRole(PermissionForm permissionForm) {
		Role role = new Role();
		Company company = companyRepository.findByname(permissionForm.getCompanyName());
		role.setCompany(company);
		role.setRoleName(permissionForm.getRoleName());
		return role;
	}

	public Map<String, Object> saveRolePermission(PermissionForm permissionForm) {
		Map<String, Object> result = saveRole(permissionForm);
		if (SUCCESS.equals(result.get(RESPONSE_MESSAGE))) {
			result = saveSubModulePermission(permissionForm);
		}
		return result;
	}

	private Map<String, Object> saveSubModulePermission(PermissionForm permissionForm) {
		Map<String, Object> result = new HashMap<>();
		SubModulePermission subModulePermission = getSubModulePermission(permissionForm);
		if (!isExistsSubModulePermission(subModulePermission)) {
			subModulePermission = subModulePermissionRepository.save(subModulePermission);
			if (subModulePermission != null) {
				result.put(MESSAGE, "subModulePermission created successfully");
			} else {
				result.put(MESSAGE, "error occured while creating subModulePermission");
				result.put(RESPONSE_MESSAGE, ERROR);
				return result;
			}
		} else {
			result.put(MESSAGE, "subModulePermission already exists");
			result.put(RESPONSE_MESSAGE, ERROR);
			return result;
		}
		result.put(RESPONSE_MESSAGE, SUCCESS);
		return result;
	}

	private boolean isExistsSubModulePermission(SubModulePermission subModulePermission) {
		boolean subModulePermissionExists = false;
		SubModulePermission oldSubModulePermission = subModulePermissionRepository
				.findByRoleAndSubModule(subModulePermission.getRole(), subModulePermission.getSubModule());
		if (oldSubModulePermission != null) {
			subModulePermissionExists = true;
		}
		return subModulePermissionExists;
	}

	private SubModulePermission getSubModulePermission(PermissionForm permissionForm) {
		SubModulePermission subModulePermission = new SubModulePermission();
		subModulePermission.setCanAccess(permissionForm.isCanAccess());
		subModulePermission.setCanApprove(permissionForm.isCanApprove());
		subModulePermission.setCanDelete(subModulePermission.isCanDelete());
		subModulePermission.setCanPrint(permissionForm.isCanPrint());
		subModulePermission.setCanSendForApproval(permissionForm.isCanSendForApproval());
		subModulePermission.setCanUpdate(permissionForm.isCanUpdate());
		subModulePermission.setCanView(permissionForm.isCanView());
		Company company = companyRepository.findByname(permissionForm.getCompanyName());
		Role role = roleRepository.findByRoleNameAndCompany(permissionForm.getRoleName(), company);
		subModulePermission.setRole(role);
		SubModule subModule = subModuleRepository.findBySubModuleName(permissionForm.getSubModuleName());
		subModulePermission.setSubModule(subModule);
		return subModulePermission;
	}

	public Map<String, Object> updateRolePermission(PermissionForm permissionForm) {
		Map<String, Object> result = new HashMap<>();
		SubModulePermission subModulePermission = getSubModulePermission(permissionForm);
		if(isExistsSubModulePermission(subModulePermission)) {
			subModulePermission = subModulePermissionRepository.save(subModulePermission);
			if (subModulePermission != null) {
				result.put(MESSAGE, "subModulePermission created successfully");
			} else {
				result.put(MESSAGE, "error occured while creating subModulePermission");
				result.put(RESPONSE_MESSAGE, ERROR);
				return result;
			}
		}else {
			result.put(MESSAGE, "role permission not found to update");
			result.put(RESPONSE_MESSAGE, ERROR);
			return result;
		}
		result.put(RESPONSE_MESSAGE, SUCCESS);
		return result;
	}

	public Map<String, Object> deleteRolePermission(PermissionForm permissionForm) {
		Map<String, Object> result = new HashMap<>();
		SubModulePermission subModulePermission = getSubModulePermission(permissionForm);
		if(isExistsSubModulePermission(subModulePermission)) {
			Role role = subModulePermission.getRole();
			subModulePermissionRepository.delete(subModulePermission);
			roleRepository.delete(role);
		}else {
			result.put(MESSAGE, "role permission not found to update");
			result.put(RESPONSE_MESSAGE, ERROR);
			return result;
		}
		result.put(RESPONSE_MESSAGE, SUCCESS);
		return result;
	}
}
