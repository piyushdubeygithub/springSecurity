package com.prosmv.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prosmv.domain.SubModule;

@Repository
public interface SubModuleRepository extends JpaRepository<SubModule, Serializable> {

	public SubModule findBySubModuleName(String subModuleName);
}
