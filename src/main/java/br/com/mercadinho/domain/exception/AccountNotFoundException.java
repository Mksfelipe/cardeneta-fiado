package br.com.mercadinho.domain.exception;

public class AccountNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public AccountNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public AccountNotFoundException(Long id) {
		this(String.format("There is no Account registration with ID: %d", id));
	}
}