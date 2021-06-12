package com.github.peshkovm.services;

import com.github.peshkovm.domains.Permission;
import com.github.peshkovm.repositories.UserRepository;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccessControlJPAService implements AccessControlService {
  private final UserRepository userRepository;

  @Override
  @Transactional
  public boolean userHasPermission(Long userId, Long permissionID) {
    return userRepository.findById(userId).stream()
        .anyMatch(
            user ->
                user.getRoles().stream()
                    .anyMatch(
                        role ->
                            role.getPermissions().stream()
                                .anyMatch(permission -> permission.getId().equals(permissionID))));
  }

  @Override
  @Transactional
  public Set<Permission> getAllUserPermissions(Long userId) {
    return userRepository.findById(userId).stream()
        .flatMap(user -> user.getRoles().stream())
        .flatMap(role -> role.getPermissions().stream())
        .collect(Collectors.toSet());
  }
}
