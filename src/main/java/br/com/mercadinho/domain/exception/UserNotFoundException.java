package br.com.mercadinho.domain.exception;

public class UserNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public UserNotFoundException(Long id) {
		this(String.format("There is no User registration with ID: %d", id));
	}
}
