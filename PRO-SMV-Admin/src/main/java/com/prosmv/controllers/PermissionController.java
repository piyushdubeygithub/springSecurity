package com.prosmv.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.prosmv.form.PermissionForm;
import com.prosmv.service.CompanyService;
import com.prosmv.service.PermissionService;
import com.prosmv.service.UserService;
import com.prosmv.util.Constant;
import com.prosmv.util.ResponseHandler;
import com.prosmv.util.URLMapping;

@RestController
public class PermissionController {

	@Autowired
	private UserService userService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private PermissionService permissionService;

	@RequestMapping(value = URLMapping.SAVE_ROLE_PERMISSIONS, method = RequestMethod.POST)
	public ResponseEntity<Object> saveRolePermission(@RequestBody PermissionForm permissionForm) {
		Map<String, Object> result = permissionService.saveRolePermission(permissionForm);
		if (Constant.SUCCESS.equals(result.get(Constant.RESPONSE_MESSAGE))) {
			return ResponseHandler.generateResponse(HttpStatus.ACCEPTED, false, Constant.SUCCESS, result);
		}
		return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, result);
	}

	@RequestMapping(value = URLMapping.UPDATE_ROLE_PERMISSIONS, method = RequestMethod.PUT)
	public ResponseEntity<Object> updateRolePermission(@RequestBody PermissionForm permissionForm) {
		Map<String, Object> result = permissionService.updateRolePermission(permissionForm);
		if (Constant.SUCCESS.equals(result.get(Constant.RESPONSE_MESSAGE))) {
			return ResponseHandler.generateResponse(HttpStatus.ACCEPTED, false, Constant.SUCCESS, result);
		}
		return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, result);
	}

	@RequestMapping(value = URLMapping.DELETE_ROLE_PERMISSIONS, method = RequestMethod.PUT)
	public ResponseEntity<Object> deleteRolePermission(@RequestBody PermissionForm permissionForm) {
		Map<String, Object> result = permissionService.deleteRolePermission(permissionForm);
		if (Constant.SUCCESS.equals(result.get(Constant.RESPONSE_MESSAGE))) {
			return ResponseHandler.generateResponse(HttpStatus.ACCEPTED, false, Constant.SUCCESS, result);
		}
		return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, result);
	}
}
