package br.com.mercadinho.api.controller;

import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadinho.api.dto.AccountDTO;
import br.com.mercadinho.api.dto.TransactionDTO;
import br.com.mercadinho.api.dto.UserDTO;
import br.com.mercadinho.domain.model.Transaction;
import br.com.mercadinho.domain.model.User;
import br.com.mercadinho.domain.service.TransactionService;
import br.com.mercadinho.domain.service.UserService;
import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/user/profile")
public class UserProfileController {

	@Autowired
	private UserService userService;

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	@RolesAllowed("USER")
	public UserDTO getUserInfo() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		AccountDTO accountDTO = new AccountDTO();
		
		if (authentication != null && authentication.isAuthenticated()) {
			User user = userService.findbyCpf(authentication.getName());
			
			
			modelMapper.map(user.getAccount(), accountDTO);
			UserDTO userDTO = new UserDTO(user);
			userDTO.setAccount(accountDTO);
			
			return userDTO;
		}
		return null;
	}

	@GetMapping("/transanctions")
	public List<TransactionDTO> getAllTransactions() {

	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    if (authentication != null && authentication.isAuthenticated()) {
	        User user = userService.findbyCpf(authentication.getName());

	        List<Transaction> transactions = transactionService.getAll(user.getAccount().getId());

	        return transactions.stream()
	                .map(TransactionDTO::new)
	                .toList();
	    }

	    return Collections.emptyList(); // ou lançar exceção apropriada
	}

}
