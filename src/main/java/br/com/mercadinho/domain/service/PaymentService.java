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

	    if (paymentDTO.getAmountPaid().compareTo(BigDecimal.ZERO) <= 0) {
	        throw new InsufficientfundsException("payment amount must be greater than zero");
	    }

	    Payment payment = new Payment();
	    modelMapper.map(paymentDTO, payment);

	    List<Transaction> paidTransactions = transactionService.transactions(account);

	    BigDecimal remainingAmount = payment.getAmountPaid();

	    for (Transaction transaction : paidTransactions) {
	        if (remainingAmount.compareTo(transaction.getAmount()) >= 0) {
	            transaction.setPaid(true);
	            transaction.setPayment(payment);
	            remainingAmount = remainingAmount.subtract(transaction.getAmount());
	        } else {
	            // Valor insuficiente para pagar essa transação totalmente
	            BigDecimal unpaidAmount = transaction.getAmount().subtract(remainingAmount);

	            transaction.setAmount(remainingAmount); // Paga apenas o que conseguiu
	            transaction.setPaid(true);
	            transaction.setPayment(payment);

	            // Cria uma nova transação para o valor restante
	            Transaction newTransaction = new Transaction();
	            newTransaction.setAccount(account);
	            newTransaction.setAmount(unpaidAmount);
	            newTransaction.setPaid(false);
	            transactionRepository.save(newTransaction);

	            remainingAmount = BigDecimal.ZERO;
	            break; // Todo o valor do pagamento já foi usado
	        }
	    }

	    BigDecimal accountBalanceAfterPayment = account.getBalance().subtract(payment.getAmountPaid());
	    account.setBalance(accountBalanceAfterPayment.max(BigDecimal.ZERO)); // nunca negativo

	    payment.setChange(accountBalanceAfterPayment.compareTo(BigDecimal.ZERO) < 0
	        ? payment.getAmountPaid().subtract(account.getBalance())
	        : BigDecimal.ZERO);
	    payment.setAccount(account);

	    accountService.save(account);
	    paymentRepository.save(payment);
	    transactionRepository.saveAll(paidTransactions);
	}


}
