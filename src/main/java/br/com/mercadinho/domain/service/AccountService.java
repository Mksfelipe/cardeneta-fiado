package br.com.mercadinho.domain.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mercadinho.domain.exception.AccountNotFoundException;
import br.com.mercadinho.domain.model.Account;
import br.com.mercadinho.domain.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Transactional
	public Account save(Account account) {

		return accountRepository.save(account);
	}

	public Account findById(Long id) {
		return findByAccount(id);
	}
	
	public BigDecimal balanceWeek(Long id) {
		return accountRepository.balanceWeek(id);
	}
	
	public BigDecimal balanceMonth(Long id) {
		return accountRepository.balanceMonth(id);
	}
	
	private Account findByAccount(Long id) {
		return accountRepository.findById(id)
				.orElseThrow(() -> new AccountNotFoundException(String.format("Account not found by ID: %d", id)));
	}
	

}
