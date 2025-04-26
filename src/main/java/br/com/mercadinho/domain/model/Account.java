package br.com.mercadinho.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "account")
@Getter
@Setter
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal balance = BigDecimal.ZERO;

	@CreationTimestamp
	private LocalDateTime created;

	@CreationTimestamp
	private LocalDateTime updated;

	@JsonIgnore
	@OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
	private User user;

	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
	private List<Transaction> transactions;

	public void setBalance(BigDecimal balance) {
		if (balance.compareTo(BigDecimal.ZERO) >= 0) {
			this.balance = balance;
		} else {
			throw new IllegalArgumentException("Balance cannot be negative.");
		}
	}
}
