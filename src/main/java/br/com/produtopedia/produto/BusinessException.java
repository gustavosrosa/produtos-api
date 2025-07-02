package br.com.produtopedia.produto;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	
	public BusinessException(String message) {
		this.message = message;
	}

}
