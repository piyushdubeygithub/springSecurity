package com.example.vikash.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.vikash.domain.User;
import com.example.vikash.domain.VerificationToken;
import com.example.vikash.enumdata.TokenType;
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Serializable> {
	
	
	VerificationToken findByTokenAndIsDeleted(String token,boolean isDeleted);
		

}
