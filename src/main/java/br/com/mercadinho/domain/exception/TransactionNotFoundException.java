package br.com.mercadinho.domain.exception;

public class TransactionNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public TransactionNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public TransactionNotFoundException(Long id) {
		this(String.format("There is no Transaction registration with ID: %d", id));
	}
}
