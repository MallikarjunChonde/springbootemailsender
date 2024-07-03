package com.arjun.service.impl;

import org.springframework.stereotype.Service;

import com.arjun.model.Confirmation;
import com.arjun.model.User;
import com.arjun.repository.ConfirmationRepository;
import com.arjun.repository.UserRepository;
import com.arjun.service.EmailServece;
import com.arjun.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final ConfirmationRepository confirmationRepository;
	private final EmailServece emailServece;
	
	@Override
	public User saveUser(User user) {
		
		if(userRepository.existsByEmail(user.getEmail())) {	throw new RuntimeException("Email already exists !!");	}
		user.setEnabled(false);
		userRepository.save(user);
		
		Confirmation confirmation= new Confirmation(user);
		confirmationRepository.save(confirmation);
		
//		Send email to user with token 
		
		//emailServece.sendSimpleMailMessage(user.getName(), user.getEmail(), confirmation.getToken());// without any attachment 
		//emailServece.sendMimeMailWithAttachments(user.getName(), user.getEmail(), confirmation.getToken());
//		emailServece.sendMimeMessageWithEmbeddedImages(user.getName(), user.getEmail(), confirmation.getToken());
//		emailServece.sendHtmlEmail(user.getName(), user.getEmail(), confirmation.getToken());
		emailServece.sendHtmlEmailWithEmbaddedFiles(user.getName(), user.getEmail(), confirmation.getToken());
		
		return user;
	}

	@Override
	public Boolean verifyToken(String token) {

		Confirmation confirmation= confirmationRepository.findByToken(token);
		User user= userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
		user.setEnabled(true);
		userRepository.save(user);
//		confirmationRepository.delete(confirmation);
		return Boolean.TRUE;
	}

}
