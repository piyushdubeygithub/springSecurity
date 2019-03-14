package com.example.vikash.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.vikash.domain.User;
import com.example.vikash.enumdata.RoleType;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Serializable>{
	
	User findByEmail(String email);
	
	User findByPassword(String password);
	
	
	User findByEmailAndIsEmailVerified(String email,boolean isEmailVerified);
	
	
	Page<User> findByRoleType(Pageable pageable,RoleType roleType);

	


}
