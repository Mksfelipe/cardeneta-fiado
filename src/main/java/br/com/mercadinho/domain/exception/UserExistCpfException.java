package br.com.mercadinho.domain.exception;

public class UserExistCpfException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public UserExistCpfException(String mensagem) {
		super(mensagem);
	}

	public UserExistCpfException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
