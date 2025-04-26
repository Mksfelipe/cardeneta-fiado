package br.com.mercadinho.domain.exception;

public class RoleException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public RoleException(String mensagem) {
		super(mensagem);
	}
	
	public RoleException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
