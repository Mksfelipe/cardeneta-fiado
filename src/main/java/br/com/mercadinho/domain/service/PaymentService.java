package br.com.mercadinho.domain.service;

import java.math.BigDecimal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mercadinho.api.dto.PaymentDTO;
import br.com.mercadinho.domain.exception.AccountBalanceZero;
import br.com.mercadinho.domain.exception.InsufficientfundsException;
import br.com.mercadinho.domain.model.Account;
import br.com.mercadinho.domain.model.Payment;
import br.com.mercadinho.domain.model.Transaction;
import br.com.mercadinho.domain.repository.PaymentRepository;
import br.com.mercadinho.domain.repository.TransactionRepository;
import jakarta.transaction.Transactional;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	public void save(Long accountId, PaymentDTO paymentDTO) {
		Account account = accountService.findById(accountId);

		if (account.getBalance().compareTo(BigDecimal.ZERO) <= 0) {
			throw new AccountBalanceZero("account balance is zero");
		}
		
		if (paymentDTO.getAmountPaid().compareTo(account.getBalance()) < 0) {
			throw new InsufficientfundsException("insufficient to make payment");
		}

		Payment payment = new Payment();
		modelMapper.map(paymentDTO, payment);

		payment.setChange(payment.getAmountPaid().subtract(account.getBalance()));
		payment.setAccount(account);

		List<Transaction> paidTransactions = transactionService.transactions(account);
		for (Transaction transaction : paidTransactions) {
			transaction.setPaid(true);
			transaction.setPayment(payment);
		}

		account.setBalance(BigDecimal.ZERO);
		accountService.save(account);

		paymentRepository.save(payment);
		transactionRepository.saveAll(paidTransactions);

	}

}
