package br.com.alura.forum.exception;

public class RegisterNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public RegisterNotFoundException() { } 
	
	public RegisterNotFoundException(String message) {
		super(message);
	}
	
}
