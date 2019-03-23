package com.winwithweb.application.service;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.winwithweb.application.model.EmailConfigurations;
import com.winwithweb.application.model.EmailDetails;

public class EmailUtility {

	public static void sendEmail(EmailDetails emaildata, EmailConfigurations emailconfig) {
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.host", emailconfig.gethostname());
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
		properties.setProperty("mail.pop3.socketFactory.fallback", "false");
		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailconfig.getsenderemail(), emailconfig.getsenderemailpassword());
			}
		});
		try {
			MimeMessage message = new MimeMessage(session);
			Address[] address = new Address[1];
			address[0] = new InternetAddress(emailconfig.getsetReplyto());
			message.setReplyTo(address);
			message.setFrom(new InternetAddress(emailconfig.getsenderemail()));

			message.setSubject(emaildata.getEmailsubject());
			//message.setText(emaildata.getEmailcontent());
			
			message.setContent(emaildata.getEmailcontent(), "text/html");

			String[] emails = emaildata.getRecepientemailIds().split(",");	
			Address[] bccaddress = new Address[emails.length];
			for(int i=0;i<emails.length;i++){
				bccaddress[i]=new InternetAddress(emails[i]);
			}
			message.setRecipients(MimeMessage.RecipientType.BCC, bccaddress);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
