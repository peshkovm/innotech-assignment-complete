package com.github.peshkovm.services;

import com.github.peshkovm.common.AccountInfo;
import java.time.LocalDateTime;
import java.util.List;

public interface AccountManagementService {

  Double getSumOfAllTransfers(Long clientId, LocalDateTime fromDate, LocalDateTime toDate);

  List<AccountInfo> getAccountInfo(Long clientId);
}
