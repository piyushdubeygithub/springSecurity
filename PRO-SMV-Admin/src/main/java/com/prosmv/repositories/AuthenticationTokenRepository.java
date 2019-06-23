package com.prosmv.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prosmv.domain.AuthenticationToken;
import com.prosmv.domain.User;

public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Serializable>{
	
	AuthenticationToken findByToken(String accessToken);
	AuthenticationToken findByUser(User user);

}
