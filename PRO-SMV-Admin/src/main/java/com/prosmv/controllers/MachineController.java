package com.prosmv.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.prosmv.dto.MachineListDTO;
import com.prosmv.dto.ResponseDTO;
import com.prosmv.form.MachineForm;
import com.prosmv.service.MachineService;
import com.prosmv.util.Constant;
import com.prosmv.util.ResponseHandler;
import com.prosmv.util.URLMapping;

@CrossOrigin
@RestController
public class MachineController {
	
	@Autowired
	private MachineService machineService;

	@RequestMapping(value = URLMapping.CREATE_MACHINE, method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO<String>> createMachine(@Valid @RequestBody MachineForm machineForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR,
					bindingResult.getAllErrors().get(0).getDefaultMessage(), null);
		}
		String response = machineService.createMachine(machineForm);
		if (response != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.OK, false, Constant.SUCCESS, response, null);
		}
		return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, false, Constant.ERROR, response, null);
	}
	
	@RequestMapping(value = URLMapping.UPDATE_MACHINE, method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO<String>> updateMachine(@Valid @RequestBody MachineForm machineForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR,
					bindingResult.getAllErrors().get(0).getDefaultMessage(), null);
		}
		String response = machineService.updateMachine(machineForm);
		if (response != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.OK, false, Constant.SUCCESS, response, null);
		}
		return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, false, Constant.ERROR, response, null);
	}
	
	@RequestMapping(value = URLMapping.DELETE_MACHINE, method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO<String>> deleteMachine(@RequestParam Long id) {
		String response = machineService.deleteMachine(id);
		if (response != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.OK, false, Constant.SUCCESS, response, null);
		}
		return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, false, Constant.ERROR, response, null);
	}
	
	@RequestMapping(value = URLMapping.GET_ALL_MACHINES, method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<MachineListDTO>> getAllMachines(@RequestParam String factoryName) {
		MachineListDTO response = machineService.getAllMachines(factoryName);
		if (response != null) {
			return ResponseHandler.generateSuccessResponse(HttpStatus.OK, Boolean.FALSE, Constant.SUCCESS, response);
		}
		return ResponseHandler.generateSuccessResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, response);
	}
}
