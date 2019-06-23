package com.prosmv.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.prosmv.domain.User;
import com.prosmv.dto.ResponseDTO;
import com.prosmv.dto.UserListDTO;
import com.prosmv.form.UserForm;
import com.prosmv.service.UserService;
import com.prosmv.util.Constant;
import com.prosmv.util.ResponseHandler;
import com.prosmv.util.URLMapping;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = URLMapping.REGISTER_USER, method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO<String>> registerUser(@Valid @RequestBody UserForm userForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR,
					bindingResult.getAllErrors().get(0).getDefaultMessage(), null);
		}
		String response = userService.saveUser(userForm);
		if (response != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.OK, false, Constant.SUCCESS, response, null);
		}
		return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, false, Constant.ERROR, response, null);
	}

	@RequestMapping(value = URLMapping.UPDATE_USER, method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO<String>> updateUser(@Valid @RequestBody UserForm userForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR,
					bindingResult.getAllErrors().get(0).getDefaultMessage(), null);
		}
		String response = userService.updateUser(userForm);
		if (response != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.OK, false, Constant.SUCCESS, response, null);
		}
		return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, false, Constant.ERROR, response, null);
	}

	@RequestMapping(value = URLMapping.USER_LIST, method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<UserListDTO>> getCompanyList(@RequestParam String companyName) {
		UserListDTO userListDTO = userService.getUserList(companyName);
		if (userListDTO != null) {
			return ResponseHandler.generateSuccessResponse(HttpStatus.OK, Boolean.FALSE, Constant.SUCCESS, userListDTO);
		}
		return ResponseHandler.generateSuccessResponse(HttpStatus.OK, Boolean.FALSE, Constant.SUCCESS, userListDTO);
	}

	@RequestMapping(value = URLMapping.DEACTIVATE_USER, method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<String>> deActivateUser(@RequestParam String username) {
		String response = userService.deActivateUser(username);
		if (response != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.OK, false, Constant.SUCCESS, response, null);
		}
		return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, false, Constant.ERROR, response, null);
	}

	@RequestMapping(value = URLMapping.ACTIVATE_USER, method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<String>> activateUser(@RequestParam String username) {
		String response = userService.activateUser(username);
		if (response != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.OK, false, Constant.SUCCESS, response, null);
		}
		return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, false, Constant.ERROR, response, null);
	}
}
