package br.com.mercadinho.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadinho.api.dto.PaymentDTO;
import br.com.mercadinho.domain.service.PaymentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/account/{accountId}/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void save(@PathVariable Long accountId, @Valid @RequestBody PaymentDTO paymentDTO) {

		paymentService.save(accountId, paymentDTO);
	}
}
