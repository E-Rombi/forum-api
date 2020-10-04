package br.com.alura.forum.controller.dto;

public class MessageDto {

	private String message;
	
	public MessageDto(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
