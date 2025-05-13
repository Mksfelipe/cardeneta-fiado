package br.com.mercadinho.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mercadinho.domain.model.Account;
import br.com.mercadinho.domain.model.Transaction;
import br.com.mercadinho.domain.model.User;

class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
    }

    @Test
    void testInitialBalanceIsZero() {
        assertEquals(BigDecimal.ZERO, account.getBalance(), "Initial balance should be ZERO.");
    }

    @Test
    void testSetValidBalance() {
        BigDecimal newBalance = new BigDecimal("150.75");
        account.setBalance(newBalance);
        assertEquals(newBalance, account.getBalance(), "Balance should be updated correctly.");
    }

    @Test
    void testSetNegativeBalanceThrowsException() {
        BigDecimal negativeBalance = new BigDecimal("-100");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            account.setBalance(negativeBalance);
        });

        assertEquals("Balance cannot be negative.", thrown.getMessage());
    }

    @Test
    void testSetAndGetTransactions() {
        var transactions = new ArrayList<Transaction>();
        account.setTransactions(transactions);
        assertEquals(transactions, account.getTransactions());
    }

    @Test
    void testSetAndGetUser() {
        var user = new User();
        account.setUser(user);
        assertEquals(user, account.getUser());
    }
}
