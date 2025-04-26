package br.com.mercadinho.domain.exception;

public class TransactionNegativeAmountException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public TransactionNegativeAmountException(String mensagem) {
		super(mensagem);
	}
	
	public TransactionNegativeAmountException() {
		this("The transaction cannot have a negative value.");
	}
}
