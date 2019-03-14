package com.example.vikash.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.vikash.domain.User;
import com.example.vikash.service.UserService;
import com.example.vikash.utils.ResponseHandler;
@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value="/user/signup" , method=RequestMethod.POST)
	ResponseEntity<Object> createUser(@RequestBody User user){
		Map result= null;
		try{
			if(user!=null)
			{
			 result=userService.userRegister(user);
			}	
			return ResponseHandler.generateResponse(HttpStatus.OK,true,"success",result);	
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,"error",result);
		}	
	}
	@RequestMapping(value="/user/verify" , method=RequestMethod.GET)
	ResponseEntity<Object> verifYUser(@RequestParam String token){		
		Map result= null;
		try{
			if(token!=null)
			{
			 result=userService.verifyUser(token);
			}	
			return ResponseHandler.generateResponse(HttpStatus.OK,true,"success",result);	
		} catch (Exception e) {
			e.printStackTrace();	
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,"error",result);
		}	
		
	}
	
	@RequestMapping(value="/user/get/byId" , method=RequestMethod.GET)
	ResponseEntity<Object> getUserById(@RequestParam Long id){		
		Map result= null;
		try{
			if(id!=null)
			{
			 result=userService.getUserById(id);
			}	
			return ResponseHandler.generateResponse(HttpStatus.OK,true,"success",result);	
		} catch (Exception e) {
			e.printStackTrace();	
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,"error",result);
		}	
		
	}
	
	@RequestMapping(value="/user/update" , method=RequestMethod.PUT)
	ResponseEntity<Object> updateUser(@RequestBody User user){		
		Map result= null;
		try{
			if(user!=null)
			{
			 result=userService.updateUser(user);
			}	
			return ResponseHandler.generateResponse(HttpStatus.OK,true,"success",result);	
		} catch (Exception e) {
			e.printStackTrace();	
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,"error",result);
		}	
		
	}
	
	@RequestMapping(value="/user/change/password" , method=RequestMethod.PUT)
	ResponseEntity<Object> changePassword(@RequestBody Map map){		
		Map result= null;
		try{
			if(map!=null){
			 result=userService.changePassword(map);
			}
			return ResponseHandler.generateResponse(HttpStatus.OK,true,"success",result);	
		} catch (Exception e) {
			e.printStackTrace();	
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,"error",result);
		}	
	}
	
	@RequestMapping(value="/user/forget/password" , method=RequestMethod.GET)
	ResponseEntity<Object> forgetPassword(@RequestParam String email){		
		Map result= null;
		try{
			if(email!=null){
			 result=userService.forgetPassword(email);
			}
			return ResponseHandler.generateResponse(HttpStatus.OK,true,"success",result);	
		} catch (Exception e) {
			e.printStackTrace();	
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,"error",result);
		}	
	}
	
	@RequestMapping(value="/user/verify/forget/password" , method=RequestMethod.PUT)
	ResponseEntity<Object> verifyForgetPassword(@RequestBody Map map,@RequestParam String token){		
		Map result= null;
		try{
			if(token!=null){
			 result=userService.verifyForgetPassword(map,token);
			}
			return ResponseHandler.generateResponse(HttpStatus.OK,true,"success",result);	
		} catch (Exception e) {
			e.printStackTrace();	
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,"error",result);
		}	
	}
	
	@RequestMapping(value="/user/facebook/signup" , method=RequestMethod.POST)
	ResponseEntity<Object> facebookSignUp(@RequestBody Map map){		
		Map result= null;
		try{
			if(map!=null){
			 result=userService.facebookSignUp(map);
			}
			return ResponseHandler.generateResponse(HttpStatus.OK,true,"success",result);	
		} catch (Exception e) {
			e.printStackTrace();	
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,"error",result);
		}	
	}
	
	
	
	@RequestMapping(value="/user/login" , method=RequestMethod.POST)
	ResponseEntity<Object> logIn(@RequestBody Map map){		
		Map result= null;
		try{
			if(map!=null){
			 result=userService.logIn(map);
			}
			return ResponseHandler.generateResponse(HttpStatus.OK,true,"success",result);	
		} catch (Exception e) {
			e.printStackTrace();	
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,"error",result);
		}	
	}
}
