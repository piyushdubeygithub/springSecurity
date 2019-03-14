package com.example.vikash.mail;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderClass {
	
	    @Autowired
	    private JavaMailSender mailsender;
	 
		public void sendEmail(String to,String subject,String text) throws MessagingException{
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);  
	        mailsender.send(message);
	         
	    }


}
