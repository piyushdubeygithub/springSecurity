package com.example.vikash.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vikash.domain.AuthenticationToken;

@Repository
public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Serializable>{

	AuthenticationToken findByToken(String accessToken);

}
