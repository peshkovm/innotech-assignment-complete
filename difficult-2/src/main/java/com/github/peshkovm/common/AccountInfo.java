package com.github.peshkovm.common;

import com.github.peshkovm.domains.Account;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AccountInfo {
  private final LocalDateTime date;
  private final OperationType debit;
  private final Account toAccount;
  private final Double sumOfTransfer;
}
