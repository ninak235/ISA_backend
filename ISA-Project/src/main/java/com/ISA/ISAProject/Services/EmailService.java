package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.EquipmentDto;
import com.ISA.ISAProject.Dto.ReservationDto;
import com.ISA.ISAProject.Model.Reservation;
import com.ISA.ISAProject.Model.User;
import com.ISA.ISAProject.Repository.AccountConfirmationTokenRepository;
import com.ISA.ISAProject.Repository.ReservationConfirmationTokenRepository;
import com.ISA.ISAProject.Repository.ReservationRepository;
import com.ISA.ISAProject.Repository.UserRepository;
import com.ISA.ISAProject.Token.AccountConfirmationToken;
import com.ISA.ISAProject.Token.ReservationConfirmationToken;
import com.ISA.ISAProject.utils.QrCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.Base64;
import java.util.List;
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
    private ReservationConfirmationTokenRepository _reservationTokenRepository;
    @Autowired
    private ReservationRepository _reservationRepository;
    @Autowired
    private QrCodeUtil qrCodeUtil;
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
            mail.setText("To confirm your account,please click here: " + "http://localhost:8080/auth/confirm-account?token=" + generateToken(userOptional.get()));
            javaMailSender.send(mail);
        }else{
            System.out.println("User not found for email: " + email);
        }
    }

    @Transactional
    public void sendReservationEmail(ReservationDto reserv, String email)throws MailException{
        Optional<Reservation> reservationOptional = _reservationRepository.findById(reserv.getId());
        if(reservationOptional.isPresent()){
            Reservation reservation = reservationOptional.get();
            ReservationDto reservationDto = new ReservationDto(reservation);
            String reservationInfo = createReservationInfoString(reservationDto);
            try{
                byte[] qrCodeBytes = qrCodeUtil.generateQrCode(reservationInfo);
                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                helper.setTo(email);
                helper.setFrom(env.getProperty("spring.mail.username"));
                helper.setSubject("Confirm Reservation!");
                helper.setText("To confirm your reservation,please click here: " + "http://localhost:8080/auth/confirm-reservation?token=" + generateReservationToken(reservation));
                helper.addInline("qrCodeImage", new ByteArrayResource(qrCodeBytes), "image/png");
                javaMailSender.send(message);

            }catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("User not found for email: " + email);
        }
    }

    private String generateToken(User user){
        AccountConfirmationToken confirmationToken = new AccountConfirmationToken(user);
        _tokenRepository.save(confirmationToken);
        return confirmationToken.getConfirmationToken();
    }

    private String generateReservationToken(Reservation reservation){
        ReservationConfirmationToken reservationToken = new ReservationConfirmationToken(reservation);
        _reservationTokenRepository.save(reservationToken);
        return reservationToken.getReservationToken();
    }

    public boolean isEmailUnique(String email){
        User user = _userRepository.findByEmailIgnoreCase(email);
        if(user != null){
            return false;
        }
        return true;
    }

    private String createReservationInfoString(ReservationDto reservationDto) {

        StringBuilder reservationInfo = new StringBuilder();
        reservationInfo.append("Reservation ID: ").append(reservationDto.getId())
                .append(", DateTime: ").append(reservationDto.getDateTime())
                .append(", Duration: ").append(reservationDto.getDuration())
                .append(", Grade: ").append(reservationDto.getGrade())
                .append(", Status: ").append(reservationDto.getStatus())
                .append(", Customer ID: ").append(reservationDto.getCustomerId())
                .append(", Company Admin ID: ").append(reservationDto.getCompanyAdminId());

        List<EquipmentDto> reservationEquipments = reservationDto.getReservationEquipments();
            reservationInfo.append(", Reservation Equipments: ");
            for (EquipmentDto equipment : reservationEquipments) {
                reservationInfo.append("[ID: ").append(equipment.getId())
                        .append(", Name: ").append(equipment.getName())
                        .append(", Description: ").append(equipment.getDescription())
                        .append(", Grade: ").append(equipment.getGrade())
                        .append(", Price: ").append(equipment.getPrice()).append("]");
            }

        return reservationInfo.toString();
    }

}
