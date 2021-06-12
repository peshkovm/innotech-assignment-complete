package com.github.peshkovm.services;

import com.github.peshkovm.common.AccountInfo;
import com.github.peshkovm.common.OperationType;
import com.github.peshkovm.domains.Account;
import com.github.peshkovm.domains.JournalEntry;
import com.github.peshkovm.repositories.ClientRepository;
import com.github.peshkovm.repositories.JournalEntryRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountManagementJPAService implements AccountManagementService {
  private final JournalEntryRepository journalEntryRepository;
  private final ClientRepository clientRepository;

  @Override
  @Transactional
  public Double getSumOfAllTransfers(Long clientId, LocalDateTime fromDate, LocalDateTime toDate) {
    return clientRepository.findById(clientId).orElseThrow().getAccounts().stream()
        .map(Account::getId)
        .mapToDouble(
            accountId ->
                StreamSupport.stream(journalEntryRepository.findAll().spliterator(), false)
                    .filter(journalEntry -> journalEntry.getToAccount().getId().equals(accountId))
                    .filter(
                        journalEntry ->
                            fromDate.compareTo(journalEntry.getDate())
                                    * journalEntry.getDate().compareTo(toDate)
                                >= 0)
                    .mapToDouble(JournalEntry::getSumOfTransfer)
                    .sum())
        .sum();
  }

  @Override
  @Transactional
  public List<AccountInfo> getAccountInfo(Long clientId) {
    return clientRepository.findById(clientId).orElseThrow().getAccounts().stream()
        .map(Account::getId)
        .flatMap(
            accountId ->
                StreamSupport.stream(journalEntryRepository.findAll().spliterator(), false)
                    .filter(
                        journalEntry ->
                            journalEntry.getToAccount().getId().equals(accountId)
                                || journalEntry.getFromAccount().getId().equals(accountId))
                    .map(
                        journalEntry -> {
                          AccountInfo accountInfo;
                          if (journalEntry.getToAccount().getId().equals(accountId)) {
                            accountInfo =
                                new AccountInfo(
                                    journalEntry.getDate(),
                                    OperationType.Crediting,
                                    journalEntry.getToAccount(),
                                    journalEntry.getSumOfTransfer());
                          } else {
                            accountInfo =
                                new AccountInfo(
                                    journalEntry.getDate(),
                                    OperationType.WriteOff,
                                    journalEntry.getFromAccount(),
                                    journalEntry.getSumOfTransfer());
                          }

                          return accountInfo;
                        }))
        .collect(Collectors.toList());
  }
}
