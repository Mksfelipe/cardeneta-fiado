package br.com.mercadinho.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
	
	private Long id;
	private BigDecimal balance = BigDecimal.ZERO;
	private LocalDateTime created;
	private LocalDateTime updated;

}