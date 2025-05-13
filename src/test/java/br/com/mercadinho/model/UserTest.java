package br.com.mercadinho.model;


import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import br.com.mercadinho.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setFirstName("João");
        user.setLastName("Silva");
        user.setEmail("joao@example.com");
        user.setPassword("senhaSegura123");
        user.setCpf("12345678909"); // sem formatação
        user.setContact("99999-9999");
        user.setAccount(new Account());
        user.setRoles(Set.of(new Role()));
    }

    @Test
    void testCpfFormatadoAutomaticamente() {
        user.formatarCpf();
        assertEquals("123.456.789-09", user.getCpf());
    }

    @Test
    void testCpfNaoNulo() {
        user.setCpf(null);
        assertThrows(NullPointerException.class, () -> user.formatarCpf());
    }

    @Test
    void testRelacionamentoComConta() {
        assertNotNull(user.getAccount());
    }

    @Test
    void testRelacionamentoComRoles() {
        assertFalse(user.getRoles().isEmpty());
    }
}
