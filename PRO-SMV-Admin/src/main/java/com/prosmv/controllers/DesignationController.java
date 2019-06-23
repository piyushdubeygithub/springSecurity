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
import com.prosmv.dto.ResponseDTO;
import com.prosmv.form.DesignationForm;
import com.prosmv.service.DesignationService;
import com.prosmv.util.Constant;
import com.prosmv.util.ResponseHandler;
import com.prosmv.util.URLMapping;

@RestController
public class DesignationController {

	@Autowired
	private DesignationService designationService;

	@RequestMapping(value = URLMapping.CREATE_DESIGNATION, method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO<String>> createDesignation(@Valid @RequestBody DesignationForm designationForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR,
					bindingResult.getAllErrors().get(0).getDefaultMessage(), null);
		}
		String response = designationService.createDesignation(designationForm);
		if (response != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.OK, false, Constant.SUCCESS, response, null);
		}
		return ResponseHandler.generateSuccessResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, null);
	}

	@RequestMapping(value = URLMapping.UPDATE_DESIGNATION, method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO<String>> updateDesignation(@Valid @RequestBody DesignationForm designationForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR,
					bindingResult.getAllErrors().get(0).getDefaultMessage(), null);
		}
		String  response = designationService.updateDesignation(designationForm);
		if (response != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.OK, false, Constant.SUCCESS, response, null);
		}
		return ResponseHandler.generateSuccessResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, null);
	}

	@RequestMapping(value = URLMapping.DELETE_DESIGNATION, method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO<String>> deleteDesignation(@RequestParam Long designationId) {
		String response = designationService.deleteDesignation(designationId);
		if (response != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.OK, false, Constant.SUCCESS, response, null);
		}
		return ResponseHandler.generateSuccessResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, null);
	}

	@RequestMapping(value = URLMapping.GET_ALL_DESIGNATIONS, method = RequestMethod.DELETE)
	public ResponseEntity<Object> getAllDesignations() {
		ResponseDTO result = designationService.getAllDesignations();
		if (Constant.SUCCESS.equals(result.getResponseMessage())) {
			return ResponseHandler.generateResponse(HttpStatus.ACCEPTED, false, Constant.SUCCESS, result);
		}
		return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, result);
	}
}
