package com.github.peshkovm.repositories;

import com.github.peshkovm.domains.Permission;
import org.springframework.data.repository.CrudRepository;

public interface PermissionRepository extends CrudRepository<Permission, Long> {}
