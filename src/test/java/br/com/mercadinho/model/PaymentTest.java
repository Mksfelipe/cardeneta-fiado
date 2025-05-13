package br.com.mercadinho.model;


import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mercadinho.domain.model.Account;
import br.com.mercadinho.domain.model.Payment;
import br.com.mercadinho.domain.model.Transaction;

class PaymentTest {

    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment();
    }

    @Test
    void testSetAndGetAmountPaid() {
        BigDecimal amount = new BigDecimal("100.00");
        payment.setAmountPaid(amount);
        assertEquals(amount, payment.getAmountPaid());
    }

    @Test
    void testSetAndGetChange() {
        BigDecimal change = new BigDecimal("5.50");
        payment.setChange(change);
        assertEquals(change, payment.getChange());
    }

    @Test
    void testSetAndGetAccount() {
        Account account = new Account();
        payment.setAccount(account);
        assertEquals(account, payment.getAccount());
    }

    @Test
    void testSetAndGetPaymentDate() {
        LocalDateTime now = LocalDateTime.now();
        payment.setPaymentDate(now);
        assertEquals(now, payment.getPaymentDate());
    }

    @Test
    void testSetAndGetTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        payment.setTransactions(transactions);
        assertEquals(transactions, payment.getTransactions());
    }
}
