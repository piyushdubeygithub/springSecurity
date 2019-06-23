package com.example.vikash.controller;

import java.lang.annotation.Documented;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.vikash.service.UserService;
import com.example.vikash.utils.ResponseHandler;
/**
 * @author piyush
 *
 */
@RestController
@RequestMapping("/api/v1")
public class AdminController {
	
	@Autowired
	UserService userService;
	/**
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value="/user/getallUser" , method=RequestMethod.GET)
	ResponseEntity<Object> getAllUser(Pageable pageable){		
		Map result= null;
		try{
			 result=userService.getAllUser(pageable);
			return ResponseHandler.generateResponse(HttpStatus.OK,true,"success",result);	
		} catch (Exception e) {
			e.printStackTrace();	
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,"error",result);
		}	
		
	}

}
