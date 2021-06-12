package com.github.peshkovm.services;

import com.github.peshkovm.domains.Permission;
import java.util.Set;

public interface AccessControlService {

  boolean userHasPermission(Long userId, Long permissionID);

  Set<Permission> getAllUserPermissions(Long userId);
}
