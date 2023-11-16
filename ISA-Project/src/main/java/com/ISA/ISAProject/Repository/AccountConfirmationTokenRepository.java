package com.ISA.ISAProject.Repository;

import com.ISA.ISAProject.Token.AccountConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountConfirmationTokenRepository  extends JpaRepository<AccountConfirmationToken, Long> {
    AccountConfirmationToken findByConfirmationToken (String confrimationToken);
}
