package com.prosmv.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prosmv.domain.Company;
import com.prosmv.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Serializable>{
 public Role findByRoleNameAndCompany(String rolename, Company company);
}
