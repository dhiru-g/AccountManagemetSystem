package PB2B.AccountManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import PB2B.AccountManagement.entities.Customer;
import PB2B.AccountManagement.entities.User;

@Service
public class EmailServiceImpl implements EmailService
{
	@Autowired
    private JavaMailSender mailSender;
     
    public void sendEmail(String sendTo, String subject, String mailBody) 
    {
    	String from = "dhairya.guravit@gmail.com";
    	String to = sendTo;
    	 
    	SimpleMailMessage message = new SimpleMailMessage();
    	 
    	message.setFrom(from);
    	message.setTo(to);
    	message.setSubject(subject);
    	message.setText(mailBody);
    	 
    	mailSender.send(message);
    }
}
