package br.com.mercadinho.domain.exception;

public class InsufficientfundsException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public InsufficientfundsException(String mensagem) {
		super(mensagem);
	}
	
	public InsufficientfundsException() {
		this("insuficiente para realizar o pagamento");
	}

}
