package com.prosmv.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.prosmv.domain.Role;
import com.prosmv.domain.SubModule;
import com.prosmv.domain.SubModulePermission;

@Repository
public interface SubModulePermissionRepository extends JpaRepository<SubModulePermission, Serializable> {

	public SubModulePermission findByRoleAndSubModule(Role role, SubModule subModule);
}
