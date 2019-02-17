package com.winwithweb.application.service;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtility {

	public static void sendEmail(String toEmail) {
		Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "smtp.zoho.com");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.debug", "true");
        properties.put("mail.store.protocol", "pop3");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.debug.auth", "true");
        properties.setProperty( "mail.pop3.socketFactory.fallback", "false");
        Session session = Session.getDefaultInstance(properties,new javax.mail.Authenticator() 
        {   @Override
            protected PasswordAuthentication getPasswordAuthentication() 
            {   return new PasswordAuthentication("relations@winwithweb.in","Anand227060@");
            }
        });
        try 
        {   MimeMessage message = new MimeMessage(session);
        Address[] address = new Address[1];
        address[0]= new InternetAddress("ashutosh.anand13@gmail.com");
        message.setReplyTo(address);
            message.setFrom(new InternetAddress("relations@winwithweb.in"));
            message.setRecipients(MimeMessage.RecipientType.TO,InternetAddress.parse(toEmail));
            message.setSubject("Testing from Utility");
            message.setText("Hi \nHow are you \nI'm mailing this from the Email Sender Utility. \nPlease let me know where is this email landing by replying me at ashutosh.anand13@gmail.com");
            Transport.send(message);
        } 
        catch (MessagingException e) 
        {   e.printStackTrace();
        }

	}

}
