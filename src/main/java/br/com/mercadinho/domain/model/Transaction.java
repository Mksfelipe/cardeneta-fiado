package br.com.mercadinho.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.mercadinho.domain.model.converter.BooleanConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Transaction {

	public Transaction(BigDecimal amount) {
		this.amount = amount;
	}

	public Transaction() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private BigDecimal amount;

	@CreationTimestamp
	@Column(name = "transaction_date")
	private LocalDateTime transactionDate;

	@Convert(converter = BooleanConverter.class)
	private Boolean paid = false;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "payment_id")
	private Payment payment;

	public void setBalance(BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) >= 0) {
			this.amount = amount;
		} else {
			throw new IllegalArgumentException("amount cannot be negative.");
		}
	}

}
