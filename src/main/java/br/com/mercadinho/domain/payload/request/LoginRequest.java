package br.com.mercadinho.domain.payload.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

	@NotBlank
	private String cpf;

	@NotBlank
	private String password;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
