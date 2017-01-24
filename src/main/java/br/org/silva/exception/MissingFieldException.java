package br.org.silva.exception;

import java.lang.reflect.Field;

import org.springframework.http.HttpStatus;

public class MissingFieldException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6786021606464933069L;
	
	private Field field;
	private final String DEFAUL_VALUE = "Campo Obrigatório!: ";
	
	public MissingFieldException(Field field) {
		this.field = field;
	}
	
	@Override
	public String getMessage() {
		return createMessage();
	}
	
	public HttpStatus getHttpStatus(){
		return HttpStatus.UNPROCESSABLE_ENTITY;
	}
	
	private String createMessage(){
		StringBuffer buffer =  new StringBuffer(DEFAUL_VALUE);
		buffer.append(Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1));
		return buffer.toString();
	}
}
