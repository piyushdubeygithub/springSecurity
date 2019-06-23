package com.prosmv.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prosmv.domain.CustomerSupplier;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerSupplier, Serializable>{

	public CustomerSupplier findByName(String customerName);
}
