package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Repository.AccountConfirmationTokenRepository;
import com.ISA.ISAProject.Token.AccountConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private AccountConfirmationTokenRepository _confirmationTokenRepository;

    public AccountConfirmationToken getConfirmationToken(String confrimationToken){
        return _confirmationTokenRepository.findByConfirmationToken(confrimationToken);
    }
}
