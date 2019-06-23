package com.prosmv.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.prosmv.domain.Company;
import com.prosmv.domain.Factory;

public interface FactoryRepository extends JpaRepository<Factory, Serializable>{
	
	public Factory findByname(String name);
	public Factory findById(Long id);
	public Factory findByNameAndCompany(String name, Company company);
}
