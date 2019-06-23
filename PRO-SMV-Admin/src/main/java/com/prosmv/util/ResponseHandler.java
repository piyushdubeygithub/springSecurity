package com.prosmv.util;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.prosmv.dto.ResponseDTO;


public class ResponseHandler {
	
	public static ResponseEntity<Object> generateResponse(HttpStatus status, boolean error,String message, Object responseObj) {
		ResponseDTO responseDTO = new ResponseDTO(); 
		try {
			responseDTO.setTimestamp(new Date());
			responseDTO.setStatus(status.value());
			responseDTO.setError(error);
			responseDTO.setMessage(message);
			responseDTO.setResponseObject(responseObj);
			return new ResponseEntity<Object>(responseDTO,status);
		} catch (Exception e) {
			responseDTO = new ResponseDTO(); 
			responseDTO.setTimestamp(new Date());
			responseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			responseDTO.setMessage(e.getMessage());
			return new ResponseEntity<Object>(responseDTO,status);
		}
	}
	
	public static <T> ResponseEntity<ResponseDTO<T>> generateSuccessResponse(HttpStatus status, boolean error,String responseMessage, String message) {
		ResponseDTO<T> responseDTO = new ResponseDTO<T>(status.value(), error, responseMessage, message);
		return new ResponseEntity<>(responseDTO, status);
	}
	
	public static <T> ResponseEntity<ResponseDTO<T>> generateSuccessResponse(HttpStatus status, boolean error,String responseMessage, T responseObj) {
		ResponseDTO<T> responseDTO = new ResponseDTO<T>(status.value(), error, responseMessage, responseObj);
		return new ResponseEntity<>(responseDTO, status);
	}
	
	public static <T> ResponseEntity<ResponseDTO<T>> generateSuccesResponse(HttpStatus status, boolean error,String responseMessage,String message, T responsObj) {
		ResponseDTO<T> responseDTO = new ResponseDTO<T>(status.value(), error, responseMessage,message, responsObj);
		return new ResponseEntity<>(responseDTO, status);
	}

	}


