package com.github.peshkovm.repositories;

import com.github.peshkovm.domains.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {}
