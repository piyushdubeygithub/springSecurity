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
import com.prosmv.dto.FactoryListDTO;
import com.prosmv.dto.ResponseDTO;
import com.prosmv.form.FactoryForm;
import com.prosmv.service.FactoryService;
import com.prosmv.util.Constant;
import com.prosmv.util.ResponseHandler;
import com.prosmv.util.URLMapping;

@RestController
public class FactoryController {

	@Autowired
	private FactoryService factoryService;
	
	@RequestMapping(value = URLMapping.REGISTER_FACTORY, method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO<String>> registerFactory(@Valid @RequestBody FactoryForm factoryForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR,
					bindingResult.getAllErrors().get(0).getDefaultMessage(), null);
		}
		String response = factoryService.saveFactory(factoryForm);
		if (response != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.OK, false, Constant.SUCCESS, response, null);
		}
		return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, response, null);
	}
	
	@RequestMapping(value = URLMapping.UPDATE_FACTORY, method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO<String>> updateFactory(@RequestBody FactoryForm factoryForm) {
		String response = factoryService.updateFactory(factoryForm);
		if (response != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.OK, false, Constant.SUCCESS, response, null);
		}
		return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, response, null);
	}
	
	@RequestMapping(value = URLMapping.FACTORY_LIST, method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<FactoryListDTO>> getFactoryList() {
		FactoryListDTO factoryListDTO = factoryService.getFactoryList();
		if (factoryListDTO != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.ACCEPTED, false, Constant.SUCCESS,null, factoryListDTO);
		}
		return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR,null, factoryListDTO);
	}
	
	@RequestMapping(value = URLMapping.DEACTIVATE_FACTORY, method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<String>> deActivateFactory(@RequestParam String factoryName) {
		String response = factoryService.deActivateFactory(factoryName);
		if (response != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.OK, false, Constant.SUCCESS, response, null);
		}
		return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, response, null);
	}
	
	@RequestMapping(value = URLMapping.ACTIVATE_FACTORY, method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<String>> activateFactory(@RequestParam String factoryName) {
		String response = factoryService.activateFactory(factoryName);
		if (response != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.OK, false, Constant.SUCCESS, response, null);
		}
		return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, response, null);
	}
}
