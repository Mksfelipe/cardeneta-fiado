package br.com.mercadinho.domain.exception;

public class UserExistEMailException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public UserExistEMailException(String mensagem) {
		super(mensagem);
	}

	public UserExistEMailException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
