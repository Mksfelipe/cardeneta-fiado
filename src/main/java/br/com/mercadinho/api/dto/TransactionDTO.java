package br.com.mercadinho.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.mercadinho.domain.model.Transaction;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO {

	private Long id;
	private BigDecimal amount;
	private LocalDateTime transactionDate;
	private Boolean paid;

	public TransactionDTO(Transaction transaction) {
		this.id = transaction.getId();
		this.transactionDate = transaction.getTransactionDate();
		this.paid = transaction.getPaid();
		this.amount = transaction.getAmount();	
	}

	public TransactionDTO(BigDecimal amount) {
		this.amount = amount;
	}

	public TransactionDTO() {
	}

}
