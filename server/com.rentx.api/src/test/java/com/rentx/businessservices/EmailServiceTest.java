package com.rentx.businessservices;

import com.rentx.businessservices.exceptions.AppException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.testng.annotations.BeforeMethod;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class EmailServiceTest {
    /**
     * test case for send email with sample data and exception handle
     */
    @Test
    void sendEmail() {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("from");
        mailMessage.setSubject("sub");
        mailMessage.setTo("to");
        mailMessage.setText("text");
        mailMessage.setBcc("Bcc");

        EmailService emailService = new EmailService();

        Exception exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> {
                    emailService.sendEmail(mailMessage);
                }
        );

    }
}