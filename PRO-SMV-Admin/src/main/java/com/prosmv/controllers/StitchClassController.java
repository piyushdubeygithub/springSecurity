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
import com.prosmv.dto.StitchClassListDTO;
import com.prosmv.form.StitchClassForm;
import com.prosmv.service.StitchClassService;
import com.prosmv.util.Constant;
import com.prosmv.util.ResponseHandler;
import com.prosmv.util.URLMapping;

@RestController
public class StitchClassController {

	@Autowired
	StitchClassService stitchClassService;

	@RequestMapping(value = URLMapping.CREATE_STITCHCLASS, method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO<String>> createStitchClass(@Valid @RequestBody StitchClassForm stitchClassForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR,
					bindingResult.getAllErrors().get(0).getDefaultMessage(), null);
		}
		String response = stitchClassService.saveStitchClass(stitchClassForm);
		if (response != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.OK, false, Constant.SUCCESS, response, null);
		}
		return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, response, null);
	}

	@RequestMapping(value = URLMapping.UPDATE_STITCHCLASS, method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO<String>> updateStitchClass(@Valid @RequestBody StitchClassForm stitchClassForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR,
					bindingResult.getAllErrors().get(0).getDefaultMessage(), null);
		}
		String response = stitchClassService.updateStitchClass(stitchClassForm);
		if (response != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.OK, false, Constant.SUCCESS, response, null);
		}
		return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, response, null);
	}

	@RequestMapping(value = URLMapping.GET_ALL_STITCHCLASSES, method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO<StitchClassListDTO>> getStitchClasses(@RequestParam String stitchClassName,
			String factoryName) {
		StitchClassListDTO stitchClassListDTO = stitchClassService.getStitchClasses(factoryName);
		if (stitchClassListDTO != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.OK, false, Constant.SUCCESS, null,
					stitchClassListDTO);
		}
		return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, null,
				stitchClassListDTO);
	}

	@RequestMapping(value = URLMapping.DELETE_STITCHCLASS, method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO<String>> deleteStitchClass(@RequestParam String stitchClassName,
			String factoryName) {
		String response = stitchClassService.deleteStitchClass(stitchClassName, factoryName);
		if (response != null) {
			return ResponseHandler.generateSuccesResponse(HttpStatus.OK, false, Constant.SUCCESS, response, null);
		}
		return ResponseHandler.generateSuccesResponse(HttpStatus.BAD_REQUEST, true, Constant.ERROR, response, null);
	}
}
