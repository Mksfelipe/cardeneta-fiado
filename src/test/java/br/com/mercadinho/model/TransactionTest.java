package br.com.mercadinho.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mercadinho.domain.model.Account;
import br.com.mercadinho.domain.model.Payment;
import br.com.mercadinho.domain.model.Transaction;

class TransactionTest {

    private Transaction transaction;

    @BeforeEach
    void setUp() {
        transaction = new Transaction();
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(transaction);
        assertFalse(transaction.getPaid()); // valor padrão = false
    }

    @Test
    void testConstructorWithAmount() {
        BigDecimal amount = new BigDecimal("200.00");
        Transaction tx = new Transaction(amount);
        assertEquals(amount, tx.getAmount());
    }

    @Test
    void testSetBalanceWithValidAmount() {
        BigDecimal amount = new BigDecimal("100.00");
        transaction.setBalance(amount);
        assertEquals(amount, transaction.getAmount());
    }

    @Test
    void testSetBalanceWithNegativeAmountThrowsException() {
        BigDecimal negativeAmount = new BigDecimal("-10.00");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            transaction.setBalance(negativeAmount);
        });

        assertEquals("amount cannot be negative.", ex.getMessage());
    }

    @Test
    void testSetAndGetTransactionDate() {
        LocalDateTime now = LocalDateTime.now();
        transaction.setTransactionDate(now);
        assertEquals(now, transaction.getTransactionDate());
    }

    @Test
    void testSetAndGetAccount() {
        Account account = new Account();
        transaction.setAccount(account);
        assertEquals(account, transaction.getAccount());
    }

    @Test
    void testSetAndGetPayment() {
        Payment payment = new Payment();
        transaction.setPayment(payment);
        assertEquals(payment, transaction.getPayment());
    }

    @Test
    void testSetAndGetPaid() {
        transaction.setPaid(true);
        assertTrue(transaction.getPaid());
    }
}
