package br.com.mercadinho.domain.payload.request;

import java.util.Set;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
 
@Getter
@Setter
public class SignupRequest {
	
	@NotBlank
	@Size(max = 20)
	private String firstName;
	
	@NotBlank
	@Size(max = 20)
	private String lastName;
	
 
    private String email;
    
    private String password;
    
    
    @Getter
    @Setter
    @CPF
    private String cpf;
    
    private Set<String> role;
  
}
