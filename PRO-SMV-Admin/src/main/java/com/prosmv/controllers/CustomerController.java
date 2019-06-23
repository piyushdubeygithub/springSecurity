package com.prosmv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.prosmv.dto.ResponseDTO;
import com.prosmv.form.CustomerForm;
import com.prosmv.service.CustomerService;
import com.prosmv.util.Constant;
import com.prosmv.util.ResponseHandler;
import com.prosmv.util.URLMapping;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = URLMapping.REGISTER_CUSTOMER, method = RequestMethod.POST)
	public ResponseEntity<Object> registerCustomer(@RequestBody CustomerForm customerForm) {
		ResponseDTO result = customerService.saveCustomer(customerForm);
		if (Constant.SUCCESS.equals(result.getResponseMessage())) {
			return ResponseHandler.generateResponse(HttpStatus.ACCEPTED, false, Constant.SUCCESS, result);
		}
		return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, result);
	}
	
	@RequestMapping(value = URLMapping.UPDATE_CUSTOMER, method = RequestMethod.PUT)
	public ResponseEntity<Object> updateCustomer(@RequestBody CustomerForm customerForm) {
		ResponseDTO result = customerService.updateCustomer(customerForm);
		if (Constant.SUCCESS.equals(result.getResponseMessage())) {
			return ResponseHandler.generateResponse(HttpStatus.ACCEPTED, false, Constant.SUCCESS, result);
		}
		return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, result);
	}
	
	@RequestMapping(value = URLMapping.ADD_BRAND, method = RequestMethod.GET)
	public ResponseEntity<Object> addBrand(@RequestParam String customerName, @RequestParam String brandName) {
		ResponseDTO result = customerService.addBrand(customerName, brandName);
		if (Constant.SUCCESS.equals(result.getResponseMessage())) {
			return ResponseHandler.generateResponse(HttpStatus.ACCEPTED, false, Constant.SUCCESS, result);
		}
		return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, result);
	}
	
	@RequestMapping(value = URLMapping.GET_ALL_BRANDS, method = RequestMethod.GET)
	public ResponseEntity<Object> getAllBrands(@RequestParam String customerName) {
		ResponseDTO result = customerService.getAllBrands(customerName);
		if (Constant.SUCCESS.equals(result.getResponseMessage())) {
			return ResponseHandler.generateResponse(HttpStatus.ACCEPTED, false, Constant.SUCCESS, result);
		}
		return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, result);
	}
	
	@RequestMapping(value = URLMapping.DEACTIVATE_CUSTOMER, method = RequestMethod.GET)
	public ResponseEntity<Object> deActivateCustomer(@RequestParam String customerName) {
		ResponseDTO result = customerService.deActivateCustomer(customerName);
		if (Constant.SUCCESS.equals(result.getResponseMessage())) {
			return ResponseHandler.generateResponse(HttpStatus.ACCEPTED, false, Constant.SUCCESS, result);
		}
		return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, result);
	}
	
	@RequestMapping(value = URLMapping.ACTIVATE_CUSTOMER, method = RequestMethod.GET)
	public ResponseEntity<Object> activateCustomer(@RequestParam String customerName) {
		ResponseDTO result = customerService.activateCustomer(customerName);
		if (Constant.SUCCESS.equals(result.getResponseMessage())) {
			return ResponseHandler.generateResponse(HttpStatus.ACCEPTED, false, Constant.SUCCESS, result);
		}
		return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, result);
	}
	
	@RequestMapping(value = URLMapping.DELETE_BRAND, method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteBrand(@RequestParam String brandName) {
		ResponseDTO result = customerService.deleteBrand(brandName);
		if (Constant.SUCCESS.equals(result.getResponseMessage())) {
			return ResponseHandler.generateResponse(HttpStatus.ACCEPTED, false, Constant.SUCCESS, result);
		}
		return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, result);
	}
}
