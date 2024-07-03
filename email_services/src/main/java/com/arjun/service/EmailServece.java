package com.arjun.service;

public interface EmailServece {
	
	void sendSimpleMailMessage(String name, String to, String token);
	void sendMimeMailWithAttachments(String name, String to, String token);
	void sendMimeMessageWithEmbeddedFiles(String name, String to, String token);
	void sendMimeMessageWithEmbeddedImages(String name, String to, String token);
	void sendHtmlEmail(String name, String to, String token);
	void sendHtmlEmailWithEmbaddedFiles(String name, String to, String token);

}
