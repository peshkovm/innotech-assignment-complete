package com.github.peshkovm.repositories;

import com.github.peshkovm.domains.JournalEntry;
import org.springframework.data.repository.CrudRepository;

public interface JournalEntryRepository extends CrudRepository<JournalEntry, Long> {}
