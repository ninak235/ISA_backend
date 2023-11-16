package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Model.User;
import com.ISA.ISAProject.Repository.AccountConfirmationTokenRepository;
import com.ISA.ISAProject.Repository.UserRepository;
import com.ISA.ISAProject.Token.AccountConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserRepository _userRepository;
    @Autowired
    private AccountConfirmationTokenRepository _tokenRepository;
    @Autowired
    private Environment env;

    @Async
    public void sendEmail(String email) throws MailException {
        Optional<User> userOptional = Optional.ofNullable(_userRepository.findByEmailIgnoreCase(email));
        if(userOptional.isPresent()) {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(email);
            mail.setFrom(env.getProperty("spring.mail.username"));
            mail.setSubject("Complete Registration!");
            mail.setText("To confirm your account,please click here: " + "http://localhost:8080/api/customer/confirm-account?token=" + generateToken(userOptional.get()));
            javaMailSender.send(mail);
        }else{
            System.out.println("User not found for email: " + email);
        }
    }

    private String generateToken(User user){
        AccountConfirmationToken confirmationToken = new AccountConfirmationToken(user);
        _tokenRepository.save(confirmationToken);
        return confirmationToken.getConfirmationToken();
    }

    public boolean isEmailUnique(String email){
        User user = _userRepository.findByEmailIgnoreCase(email);
        if(user != null){
            return false;
        }
        return true;
    }
}
