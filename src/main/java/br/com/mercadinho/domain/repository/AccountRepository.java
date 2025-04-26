package br.com.mercadinho.domain.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.mercadinho.domain.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query("SELECT a FROM Account a WHERE a.id = :id")
	Optional<Account> findById(Long id);

	@Query(nativeQuery = true, value = "SELECT coalesce(SUM(amount), 0.00) FROM transaction t WHERE EXTRACT(WEEK FROM t.transaction_date) = "
			+ "EXTRACT(WEEK FROM CURRENT_DATE) and account_id = :id")
	BigDecimal balanceWeek(Long id);
	
	@Query(nativeQuery = true, value = "SELECT coalesce(SUM(amount), 0.00) FROM transaction t WHERE EXTRACT(Month FROM t.transaction_date) = "
			+ "EXTRACT(Month FROM CURRENT_DATE) and account_id = :id")
	BigDecimal balanceMonth(Long id);
}
