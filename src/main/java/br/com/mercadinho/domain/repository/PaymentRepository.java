package br.com.mercadinho.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mercadinho.domain.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
