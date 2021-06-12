package com.github.peshkovm.repositories;

import com.github.peshkovm.domains.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {}
