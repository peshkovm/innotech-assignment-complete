package com.github.peshkovm.common;

import com.github.peshkovm.domains.Account;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AccountInfo {

  public AccountInfo(LocalDateTime date, OperationType debit, Account toAccount,
      Double sumOfTransfer) {

  }
}
