package com.arjun.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class MessageModel {

	private String to;
	private String subject;
	private String text;
	
	
	
}