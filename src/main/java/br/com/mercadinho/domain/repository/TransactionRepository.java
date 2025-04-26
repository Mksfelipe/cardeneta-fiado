package br.com.mercadinho.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.mercadinho.domain.model.Account;
import br.com.mercadinho.domain.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	@Query("SELECT t FROM Transaction t WHERE t.account = :account AND t.paid = false")
	Page<Transaction> findTransactionsByAccountId(Account account, Pageable pageable);
	
	@Query("SELECT t FROM Transaction t WHERE t.account = :account AND t.paid = false")
	List<Transaction> findTransactionsByAccountId(Account account);
	
}
