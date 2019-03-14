package com.example.vikash.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.vikash.domain.User;
import com.example.vikash.repository.UserRepository;

@Service
public class AdminService {
	@Autowired
	UserRepository userRepository;
	
	public Map getAllUser(Pageable pageable) throws Exception {
		Map result=new HashMap();
		Page<User> userList=userRepository.findAll(pageable);
		result.put("userList", userList);
		return result;
	}

}
