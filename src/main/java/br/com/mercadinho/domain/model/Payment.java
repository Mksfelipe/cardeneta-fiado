package br.com.mercadinho.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payment")
@Getter
@Setter
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "amount_paid")
	private BigDecimal amountPaid;
	
	@Column(name = "change_amount")
	private BigDecimal change;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	@Column(name = "payment_date")
	@CreationTimestamp
	private LocalDateTime paymentDate;
	
	@OneToMany(mappedBy = "payment", fetch = FetchType.LAZY)
	private List<Transaction> transactions;
}
