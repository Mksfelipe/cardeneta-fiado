package br.com.mercadinho.domain.exception;

public class AccountBalanceZero extends NegocioException {

	private static final long serialVersionUID = 1L;

	public AccountBalanceZero(String mensagem) {
		super(mensagem);
	}
	
	public AccountBalanceZero() {
		this("account balance is zero");
	}

}
