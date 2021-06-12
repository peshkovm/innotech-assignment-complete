package com.github.peshkovm.bootstrap;

import com.github.peshkovm.domains.Account;
import com.github.peshkovm.domains.Client;
import com.github.peshkovm.domains.JournalEntry;
import com.github.peshkovm.repositories.AccountRepository;
import com.github.peshkovm.repositories.ClientRepository;
import com.github.peshkovm.repositories.JournalEntryRepository;
import com.github.peshkovm.services.AccountManagementService;
import java.time.LocalDateTime;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
  @Autowired private AccountRepository accountRepository;
  @Autowired private ClientRepository clientRepository;
  @Autowired private JournalEntryRepository journalEntryRepository;
  @Autowired private AccountManagementService service;

  @Override
  public void run(String... args) throws Exception {
    final Client client1 = new Client(new HashSet<>());
    final Client client2 = new Client(new HashSet<>());
    clientRepository.save(client1);
    clientRepository.save(client2);

    final Account account1 = new Account();
    final Account account2 = new Account();
    final Account account3 = new Account();
    accountRepository.save(account1);
    accountRepository.save(account2);
    accountRepository.save(account3);

    final JournalEntry journalEntry1 =
        new JournalEntry(LocalDateTime.of(2021, 12, 6, 21, 13), 1000D);
    final JournalEntry journalEntry2 =
        new JournalEntry(LocalDateTime.of(2021, 12, 6, 21, 14), 2000D);
    final JournalEntry journalEntry3 =
        new JournalEntry(LocalDateTime.of(2021, 12, 6, 21, 15), 3000D);
    journalEntryRepository.save(journalEntry1);
    journalEntryRepository.save(journalEntry2);
    journalEntryRepository.save(journalEntry3);

    client1.getAccounts().add(account1);
    account1.setClient(client1);
    client1.getAccounts().add(account3);
    account3.setClient(client1);
    client2.getAccounts().add(account2);
    account2.setClient(client2);
    clientRepository.save(client1);
    clientRepository.save(client2);
    accountRepository.save(account1);
    accountRepository.save(account2);
    accountRepository.save(account3);

    journalEntry1.setFromAccount(account2);
    journalEntry1.setToAccount(account1);
    journalEntryRepository.save(journalEntry1);

    journalEntry2.setFromAccount(account2);
    journalEntry2.setToAccount(account3);
    journalEntryRepository.save(journalEntry2);

    journalEntry3.setFromAccount(account2);
    journalEntry3.setToAccount(account1);
    journalEntryRepository.save(journalEntry3);

    clientRepository.save(client1);
    clientRepository.save(client2);

    accountRepository.save(account1);
    accountRepository.save(account2);
    accountRepository.save(account3);

    journalEntryRepository.save(journalEntry1);
    journalEntryRepository.save(journalEntry2);
    journalEntryRepository.save(journalEntry3);

    final Double sumOfAllTransfers =
        service.getSumOfAllTransfers(
            1L, LocalDateTime.of(2021, 12, 6, 21, 13), LocalDateTime.of(2021, 12, 6, 21, 14));

    System.out.println("sumOfAllTransfers = " + sumOfAllTransfers);
  }
}
