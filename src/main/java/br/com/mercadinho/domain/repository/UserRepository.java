package br.com.mercadinho.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mercadinho.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u JOIN FETCH u.account WHERE u.cpf = :cpf")
	Optional<User> findByCpf(@Param("cpf") String cpf);	
	
	@Query("SELECT u FROM User u")
	Page<User> findAll(Pageable pageable);

	Boolean existsByEmail(String email);
	
	Boolean existsByCpf(String cpf);
	
	@Query("SELECT u FROM User u WHERE CONCAT(u.firstName, ' ', u.lastName) ILIKE %:fullName%")
    Page<User> searchByFullName(@Param("fullName") String fullName, Pageable pageable);
	
	@Query("SELECT u FROM User u JOIN FETCH u.account WHERE u.id = :id")
	Optional<User> findById(Long id);
}
