package com.prosmv.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prosmv.domain.Company;
import com.prosmv.domain.User;
@Repository
public interface CompanyRepository extends JpaRepository<Company, Serializable>{
	
	public Company findByname(String name);
	public Company findById(Long id);

}
