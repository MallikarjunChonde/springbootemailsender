package com.arjun.service.impl;

import java.io.File;
import java.util.Date;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.arjun.service.EmailServece;
import com.arjun.util.MessageModel;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.BodyPart;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailServece {

	private static final String UTF_8 = "UTF-8";
	private static final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account Verification";
	private static final String TEXT_HTML = "text/html";
	private final JavaMailSender javaMailSender;
	private final TemplateEngine  templateEngine;

	public void sendMailMessage(MessageModel model) throws MessagingException {
		MimeMessage message= javaMailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message, true);

		helper.setTo(model.getTo());
		helper.setSubject(model.getSubject());
		helper.setSentDate(new Date());
		helper.setText(model.getText(), true);
		javaMailSender.send(message);
	}

	@Override
	@Async
	public void sendSimpleMailMessage(String name, String to, String token) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
			message.setFrom("Sender/Host_Mail Address");
			message.setTo(to);
			message.setText("Dear " + name + ",\n\n"
					+ "Welcome to our Dark Club!\n\n"
					+ "Thank you for joining us. We are excited to have you as a member.\n\n"
					+ "This is a test email message to ensure everything is working correctly.\n\n"
					+ "If you have any questions or need assistance, please feel free to reach out.\n\n"
					+ "Best regards,\n"
					+ "Mallikarjun Chonde\n"
					+ "Contact : Sender/Host_Mail Address");

			javaMailSender.send(message);
		} catch (Exception e) {
			System.out.println("Error sending email: " + e.getMessage());
			throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
		}
	}

	@Override
	@Async
	public void sendMimeMailWithAttachments(String name, String to, String token) {
		try {
			MimeMessage message = getMineMessage();
			MimeMessageHelper helper= new MimeMessageHelper(message,true,UTF_8);
			helper.setPriority(1); //it is help to make your email important so it can not endup with the spam.
			helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
			helper.setFrom("Sender/Host_Mail Address");
			helper.setTo(to);
			helper.setText("Dear " + name + ",\n\n"
					+ "Welcome to our Email Testing \n\n"
					+ "Thank you for joining us. We are excited to have you as a member.\n\n"
					+ "This is a test email message to ensure Attcahments are sending correctly.\n\n"
					+ "If you have any questions or need assistance, please feel free to reach out.\n\n"
					+ "Best regards,\n"
					+ "Mallikarjun Chonde\n"
					+ "Contact : Sender/Host_Mail Address");
			//Add Attachments 
			FileSystemResource resume= new FileSystemResource(new File(System.getProperty("user.home")+"/Downloads/Images/Resume.pdf"));
			FileSystemResource image= new FileSystemResource(new File(System.getProperty("user.home")+"/Downloads/Images/angular.jpg"));

			helper.addAttachment(resume.getFilename(),resume);
			helper.addAttachment(image.getFilename(),image);

			javaMailSender.send(message);
		} catch (Exception e) {
			System.out.println("Error sending email: " + e.getMessage());
			throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
		}
	}


	@Override
	@Async
	public void sendMimeMessageWithEmbeddedImages(String name, String to, String token) {
		try {
			MimeMessage message = getMineMessage();
			MimeMessageHelper helper= new MimeMessageHelper(message,true,UTF_8);
			helper.setPriority(1); //it is help to make your email important so it can not endup with the spam.
			helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
			helper.setFrom("Sender/Host_Mail Address");
			helper.setTo(to);
			helper.setText("Dear " + name + ",\n\n"
					+ "Welcome to our Email Testing \n\n"
					+ "Thank you for joining us. We are excited to have you as a member.\n\n"
					+ "This is a test email message to ensure Attcahments are sending correctly.\n\n"
					+ "If you have any questions or need assistance, please feel free to reach out.\n\n"
					+ "Best regards,\n"
					+ "Mallikarjun Chonde\n"
					+ "Contact : Sender/Host_Mail Address");
			//Add Attachments 
			FileSystemResource resume= new FileSystemResource(new File(System.getProperty("user.home")+"/Downloads/Images/Resume.pdf"));
			FileSystemResource image= new FileSystemResource(new File(System.getProperty("user.home")+"/Downloads/Images/angular.jpg"));

			helper.addInline(getContentId(resume.getFilename()),resume);
			helper.addInline(getContentId(image.getFilename()),image);

			javaMailSender.send(message);
		} catch (Exception e) {
			System.out.println("Error sending email: " + e.getMessage());
			throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
		}
	}



	@Override
	@Async
	public void sendMimeMessageWithEmbeddedFiles(String name, String to, String token) {
		try {
			MimeMessage message = getMineMessage();
			MimeMessageHelper helper= new MimeMessageHelper(message,true,UTF_8);
			helper.setPriority(1); //it is help to make your email important so it can not endup with the spam.
			helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
			helper.setFrom("Sender/Host_Mail Address");
			helper.setTo(to);
			helper.setText("Dear " + name + ",\n\n"
					+ "Welcome to our Email Testing \n\n"
					+ "Thank you for joining us. We are excited to have you as a member.\n\n"
					+ "This is a test email message to ensure Attcahments are sending correctly.\n\n"
					+ "If you have any questions or need assistance, please feel free to reach out.\n\n"
					+ "Best regards,\n"
					+ "Mallikarjun Chonde\n"
					+ "Contact : Sender/Host_Mail Address");
			//Add Attachments 
			FileSystemResource resume= new FileSystemResource(new File(System.getProperty("user.home")+"/Downloads/Images/Resume.pdf"));
			FileSystemResource image= new FileSystemResource(new File(System.getProperty("user.home")+"/Downloads/Images/angular.jpg"));

			helper.addInline(getContentId(resume.getFilename()),resume);
			helper.addInline(getContentId(image.getFilename()),image);

			javaMailSender.send(message);
		} catch (Exception e) {
			System.out.println("Error sending email: " + e.getMessage());
			throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
		}
	}


	@Override
	@Async
	public void sendHtmlEmail(String name, String to, String token) {
		try {
			Context context= new Context();
			
			context.setVariable("name", name);
			context.setVariable("url", getVerificationUrl(name, token));
			String text= templateEngine.process("emailTemplate.html", context);
			
			MimeMessage message = getMineMessage();
			MimeMessageHelper helper= new MimeMessageHelper(message,true,UTF_8);
			helper.setPriority(1); //it is help to make your email important so it can not endup with the spam.
			helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
			helper.setFrom("Sender/Host_Mail Address");
			helper.setTo(to);
			helper.setText(text,true);

			javaMailSender.send(message);
		} catch (Exception e) {
			System.out.println("Error sending email: " + e.getMessage());
			throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
		}
	}

	

	@Override
	@Async
	public void sendHtmlEmailWithEmbaddedFiles(String name, String to, String token) {
		try {
			MimeMessage message = getMineMessage();
			MimeMessageHelper helper= new MimeMessageHelper(message,true,UTF_8);
			helper.setPriority(1); //it is help to make your email important so it can not end up with in spam.
			helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
			helper.setFrom("Sender/Host_Mail Address");
			helper.setTo(to); 
//			helper.setText(text,true);
			Context context= new Context();
			context.setVariable("name", name);
			context.setVariable("url", getVerificationUrl(name, token));
			String text= templateEngine.process("emailTemplate.html", context);
			
//			Add HTML email Body
			MimeMultipart mimeMultipart= new MimeMultipart("related");
			BodyPart messageBodyPart= new MimeBodyPart();
			messageBodyPart.setContent(text, TEXT_HTML);
			mimeMultipart.addBodyPart(messageBodyPart);
			
//			Add Image to email Body
			BodyPart imageBodyPart= new MimeBodyPart();
			DataSource dataSource= new FileDataSource(System.getProperty("user.home")+"/Downloads/Images/logo.png");
			imageBodyPart.setDataHandler(new DataHandler(dataSource)); 
			imageBodyPart.setHeader("Content-ID", "image");
			mimeMultipart.addBodyPart(imageBodyPart);
			
			message.setContent(mimeMultipart);
			
			javaMailSender.send(message);
		} catch (Exception e) {
			System.out.println("Error sending email: " + e.getMessage());
			throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
		}
	}

	//		----------------------------------------

	private MimeMessage getMineMessage() {

		return javaMailSender.createMimeMessage();

	}

	private String getContentId(String filename) {

		return "<"+ filename +">";

	}

	public static String getVerificationUrl(String name, String token ) {
		return name +"/api/users?token="+ token;
	}
}
