package br.com.mercadinho.api.dto;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.mercadinho.domain.model.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDateTime created;
	private LocalDateTime updated;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private AccountDTO account;

	@CPF
	@NotEmpty
	private String cpf;

	private String contact;

	public UserDTO(User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.created = user.getCreated();
		this.updated = user.getUpdated();
		this.cpf = user.getCpf();
		this.contact = user.getContact();
	}

	public UserDTO() {
	}

}
