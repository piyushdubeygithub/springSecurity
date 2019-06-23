package com.prosmv.controllers;

import javax.servlet.http.HttpServletRequest;
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
import com.prosmv.dto.CompanyListDTO;
import com.prosmv.dto.ResponseDTO;
import com.prosmv.form.CompanyForm;
import com.prosmv.service.*;
import com.prosmv.util.*;

@RestController
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = URLMapping.REGISTER_COMPANY, method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO<ResponseDTO<String>>> registerUser(@Valid @RequestBody CompanyForm companyForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR,
					bindingResult.getAllErrors().get(0).getDefaultMessage(), null);
		}
		String response = companyService.saveCompany(companyForm);
		if (response != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.OK, false, Constant.SUCCESS, response, null);
		}
		return ResponseHandler.generateSuccessResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, null);
	}

	@RequestMapping(value = URLMapping.UPDATE_COMPANY, method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO<String>> updateCompany(@RequestBody CompanyForm companyForm) {
		String response = companyService.updateCompany(companyForm);
		if (response != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.OK, false, Constant.SUCCESS, response, null);
		}
		return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, false, Constant.ERROR, response, null);
	}

	@RequestMapping(value = URLMapping.COMPANY_LIST, method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<CompanyListDTO>> getCompanyList(HttpServletRequest request) {
		CompanyListDTO result = companyService.getCompanyList();
		if (result != null) {
			return ResponseHandler.generateSuccessResponse(HttpStatus.OK, Boolean.FALSE, Constant.SUCCESS, result);
		}
		return ResponseHandler.generateSuccessResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, result);
	}

	@RequestMapping(value = URLMapping.DEACTIVATE_COMPANY, method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<String>> deActivateCompany(@RequestParam String name) {
		String response = companyService.deActivateCompany(name);
		if (response != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.OK, false, Constant.SUCCESS, response, null);
		}
		return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, false, Constant.ERROR, response, null);
	}

	@RequestMapping(value = URLMapping.ACTIVATE_COMPANY, method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<String>> activateCompany(@RequestParam String name) {
		String response = companyService.activateCompany(name);
		if (response != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.OK, false, Constant.SUCCESS, response, null);
		}
		return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, response, null);
	}
}
